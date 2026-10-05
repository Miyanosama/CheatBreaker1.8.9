"""Restore checked exception declarations stripped by the obfuscator."""
import collections,json,pathlib,re,sys
ROOT=pathlib.Path(__file__).resolve().parents[1];refs={}
for file in ['reference-exceptions','junixsocket-exceptions','netty-exceptions','websocket-exceptions','log4j-exceptions','json-exceptions','jlayer-exceptions','junixsocket-era-exceptions','junit-exceptions','junit-3.8.2','native-lib-exceptions','native-lib-2.1.4','word-wrap-0.1.11']:
    refs.update({c['name']:c for c in json.loads((ROOT/('.target/'+file+'.json')).read_text('utf-8'))})
target={c['name']:c for c in json.loads((ROOT/'.target/current-source-symbols.json').read_text('utf-8'))}
def fp(m):
    out=[]
    for i in m['ins']:
        a=list(i)
        if len(a)>2 and a[1]in ('method','field'):a[3]='@' if a[3]!='<init>' else '<init>'
        if len(a)>2 and a[1]=='jump':a=a[:2]
        if a[0] in (9,10):a=['const',a[0]-9]
        out.append(json.dumps(a,sort_keys=True))
    return tuple(out)
# Use an exact delegated-bytecode match for methods whose names remain placeholders.
for owner,c in list(refs.items()):
    if owner not in target:continue
    extra=[]
    for m in target[owner]['methods']:
        if not m['name'].startswith('method_'):continue
        candidates=[rm for rm in c['methods'] if rm['desc']==m['desc']]
        exact=[rm for rm in candidates if fp(rm)==fp(m)]
        if len(exact)==1:candidates=exact
        if len(candidates)==1 and candidates[0].get('exceptions'):
            extra.append(dict(candidates[0],name=m['name']))
    c['methods']+=extra
audit=[]
def arity(args):
    return len(re.findall(r'\[*[ZBCSIFJD]|L[^;]+;',args))
def paramtypes(args):
    return [({'Z':'boolean','B':'byte','C':'char','S':'short','I':'int','F':'float','J':'long','D':'double'}.get(t.lstrip('['),t.lstrip('[')[1:-1].rsplit('/',1)[-1].rsplit('$',1)[-1]))+'[]'*t.count('[') for t in re.findall(r'\[*[ZBCSIFJD]|\[*L[^;]+;',args)]
for p in (ROOT/'src/main/java').rglob('*.java'):
    owner=p.relative_to(ROOT/'src/main/java').with_suffix('').as_posix();candidates=[c for n,c in refs.items() if n==owner or n.startswith(owner+'$')]
    if len(sys.argv)>1 and not owner.startswith(sys.argv[1]):continue
    if not candidates:continue
    methods=collections.defaultdict(list)
    for c in candidates:
        for m in c['methods']:
            name=c['name'].rsplit('$',1)[-1].rsplit('/',1)[-1] if m['name']=='<init>' else m['name']
            methods[(name,arity(m['desc'].split(')',1)[0][1:]))].append(m)
    s=p.read_text('utf-8');pattern=re.compile(r'(?m)^([ \t]*(?:(?:public|private|protected|static|final|synchronized|native|abstract)\s+)*(?:[\w.$<>?,\[\] ]+\s+)?)([\w$]+)\s*\(([^{};]*)\)(\s+throws\s+[^{};]+)?(?=\s*[;{])')
    def repair(match):
        if not match[1].strip() or match[1].strip().split()[0] in ('return','throw','new','else','case'):return match[0]
        args=match[3];depth=0;count=0 if not args.strip() else 1
        for ch in args:
            if ch=='<':depth+=1
            elif ch=='>':depth-=1
            elif ch==',' and depth==0:count+=1
        possible=methods.get((match[2],count),[])
        if not possible:return match[0]
        ex={tuple(m.get('exceptions',[]))for m in possible}
        if len(ex)!=1:return match[0]
        exceptions=next(iter(ex))
        if any('^T' in m.get('signature','') for m in possible) and match[4]:return match[0]
        if not exceptions:return match[0]
        existing=[x.strip() for x in match[4].strip()[7:].split(',')] if match[4] else []
        for e in exceptions:
            value=e.replace('/','.').replace('$','.')
            if value not in existing and value.rsplit('.',1)[-1] not in existing:existing.append(value)
        text=match[1]+match[2]+'('+args+') throws '+', '.join(existing)
        if text!=match[0]:audit.append({'source':p.relative_to(ROOT).as_posix(),'method':match[2],'exceptions':existing})
        return text
    # Match individual declaration lines to avoid catastrophic backtracking on
    # large decompiler methods and to include implicit public interface members.
    out=[]
    for line in s.splitlines(keepends=True):
        if '(' not in line or ')' not in line or not re.search(r'\)\s*(?:throws [\w.$, ]+)?\s*[;{]',line):out.append(line);continue
        left,argsrest=line.split('(',1);nm=re.search(r'(\w+)\s*$',left)
        if not nm:out.append(line);continue
        prefix=left[:nm.start()];clean=prefix.strip()
        if not clean or clean.split()[0] in ('return','throw','new','else','case','if','while','switch','catch') or any(x in prefix for x in ('=','(',')')) or prefix.rstrip().endswith('.'):
            out.append(line);continue
        args,rest=argsrest.split(')',1);count=0 if not args.strip() else 1;depth=0
        for ch in args:
            if ch=='<':depth+=1
            elif ch=='>':depth-=1
            elif ch==',' and depth==0:count+=1
        possible=methods.get((nm[1],count),[])
        erased=args
        while re.search(r'<[^<>]*>',erased):erased=re.sub(r'<[^<>]*>','',erased)
        st=[]
        for arg in erased.split(',') if erased.strip() else []:
            arg=re.sub(r'\bfinal\s+','',arg.strip());ty=arg.rsplit(' ',1)[0].strip().replace('...','[]');st.append(ty.rsplit('.',1)[-1])
        exact=[m for m in possible if paramtypes(m['desc'].split(')',1)[0][1:])==st]
        if exact:possible=exact
        ex={tuple(m.get('exceptions',[]))for m in possible}
        if len(ex)!=1 or not next(iter(ex)):out.append(line);continue
        before=rest;term=re.match(r'\s*(?:throws\s+([\w.$, ]+))?(\s*[;{].*)',rest)
        if not term:out.append(line);continue
        existing=[x.strip()for x in term[1].split(',')] if term[1] else []
        for e in next(iter(ex)):
            value=e.replace('/','.').replace('$','.')
            if value not in existing and value.rsplit('.',1)[-1] not in existing:existing.append(value)
        rest=' throws '+', '.join(existing)+' '+term[2].lstrip()
        revised=left+'('+args+')'+rest+('\n' if line.endswith('\n') else '')
        out.append(revised)
        if revised!=line:audit.append({'source':p.relative_to(ROOT).as_posix(),'method':nm[1],'exceptions':existing})
    out=''.join(out)
    if out!=s:p.write_text(out,'utf-8')
(ROOT/'recovery/restored-exception-declarations.json').write_text(json.dumps(audit,indent=2),'utf-8');print('Restored exception declarations',len(audit))
