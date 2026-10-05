"""Evidence-based offline class/member matcher. No source is copied from references."""
import collections, csv, hashlib, json, pathlib, re, zipfile

ROOT=pathlib.Path(__file__).resolve().parents[1]
def load(name):return {c['name']:c for c in json.loads((ROOT/'.target'/name).read_text('utf-8'))}
P=load('preview.json'); V=load('optifine-vanilla.json'); C=load('cbz.json')
for extra in ['log4j.json','Java-WebSocket-1.5.2.json','jlayer.json','junixsocket.json','junit-3.8.1.json','vecmath-1.5.2.json','json-20160810.json','slf4j-api-1.7.25.json','native-lib-loader-2.3.4.json']:
    V.update(load(extra))
CM={}; FM={}; MM={}; E={}; PAIRS={}
def obf(s):return bool(re.fullmatch('[Il]{20,}',s))
def add(p,r,kind,reason):
    if p not in P or r not in (V if kind=='vanilla' else C):return False
    target=VAN_CLASSES.get(r,r) if kind=='vanilla' else r
    if p in CM:return CM[p]==target
    if target in CM.values():return False
    CM[p]=target;PAIRS[p]=(r,kind);E[p]=reason;return True

# Public MCP data, independent of both client implementations.
with zipfile.ZipFile(ROOT/'.target/tools/mcp-1.8.9-srg.zip') as z: srg=z.read('joined.srg').decode().splitlines()
with zipfile.ZipFile(ROOT/'.target/tools/mcp-stable-22.zip') as z:
    csvnames={}
    for n in ['fields.csv','methods.csv']:
        csvnames.update({r['searge']:r['name'] for r in csv.DictReader(z.read(n).decode().splitlines())})
VAN_CLASSES={};VAN_FIELDS={};VAN_METHODS={}
for l in srg:
    a=l.split()
    if a[0]=='CL:':VAN_CLASSES[a[1]]=a[2]
    elif a[0]=='FD:':VAN_FIELDS[a[1]]=csvnames.get(a[2].rsplit('/',1)[-1],a[2].rsplit('/',1)[-1])
    elif a[0]=='MD:':VAN_METHODS[a[1]+a[2]]=csvnames.get(a[3].rsplit('/',1)[-1],a[3].rsplit('/',1)[-1])
for n in load('optifine.json'):
    if '/' not in n:VAN_CLASSES[n]='net/minecraft/src/'+n

def table(c):return len(c['methods'])==1 and c['methods'][0]['name']=='<clinit>' and len(c['fields'])==1 and c['fields'][0]['desc']=='[Ljava/lang/String;'
for n in P:
    if n in V:add(n,n,'vanilla','unchanged public class name')
    elif n in C:add(n,n,'cbz','unchanged public class name')
SEMANTIC=json.loads((ROOT/'recovery/semantic-class-names.json').read_text('utf-8'))
for p,n in SEMANTIC.items():
    if p not in P:continue
    # User-facing constructor labels and class role establish a useful semantic
    # name; this is explicitly distinct from recovering the historical name.
    if n not in CM.values():
        CM[p]=n;E[p]='semantic name from constructor labels and class role'
        if n in C:PAIRS[p]=(n,'cbz')

def strings(c):return {i[1] for m in c['methods'] for i in m['ins'] if i[0]=='const' and isinstance(i[1],str) and len(i[1])>=3}
PS={n:strings(c) for n,c in P.items()}; VS={n:strings(c) for n,c in V.items()}; CS={n:strings(c) for n,c in C.items()}
def genericdesc(desc,classes):return re.sub(r'L([^;]+);',lambda m:'L@;' if m[1] in classes else m[0],desc)
def fp(m,classes,coarse=False):
    result=[]
    for i in m['ins']:
        v=list(i)
        if len(v)>2 and v[1] in ('field','method'):
            internal=v[2] in classes;v[2]='@' if internal else v[2];v[3]='<init>' if v[3]=='<init>' else '@' if internal else v[3];v[4]=genericdesc(v[4],classes)
        elif len(v)>2 and v[1]=='type':v[2]='@' if v[2] in classes else genericdesc(v[2],classes)
        elif len(v)>2 and v[1]=='jump':v=v[:2]
        elif v[0]=='const' and isinstance(v[1],dict) and 'type' in v[1]:v[1]={'type':genericdesc(v[1]['type'],classes)}
        if coarse and len(v)>2 and v[1] in ('var','inc'):v=v[:2]+([v[-1]] if v[1]=='inc' else [])
        result.append(v)
    return hashlib.sha256(json.dumps([genericdesc(m['desc'],classes),result],sort_keys=True).encode()).hexdigest()

# Methods with enough instructions and globally unique normalized fingerprints.
for ref,kind in [(V,'vanilla'),(C,'cbz')]:
    pi=collections.defaultdict(set);ri=collections.defaultdict(set)
    for n,c in P.items():
        if '/' in n:continue
        for m in c['methods']:
            if len(m['ins'])>=12:pi[fp(m,P)].add(n)
    for n,c in ref.items():
        if kind=='cbz' and not n.startswith(('com/cheatbreaker/','net/minecraft/src/')):continue
        for m in c['methods']:
            if len(m['ins'])>=12:ri[fp(m,ref)].add(n)
    votes=collections.defaultdict(collections.Counter)
    for key,names in pi.items():
        if len(names)==1 and len(ri.get(key,[]))==1:votes[next(iter(names))][next(iter(ri[key]))]+=1
    for p,vs in sorted(votes.items(),key=lambda x:-max(x[1].values())):
        winners=vs.most_common()
        if len(winners)==1 or winners[0][1]>winners[1][1]*2:add(p,winners[0][0],kind,'unique normalized method fingerprint (%d)'%winners[0][1])
    print('fingerprints',kind,len(CM))

# Unique strings plus agreement with the full string set, not display-name guesses.
for rs,ref,kind in [(VS,V,'vanilla'),(CS,C,'cbz')]:
    pi=collections.defaultdict(set);ri=collections.defaultdict(set)
    for n,ss in PS.items():
        if '/' not in n and not table(P[n]):
            for s in ss:pi[s].add(n)
    for n,ss in rs.items():
        if kind=='cbz' and not n.startswith(('com/cheatbreaker/','net/minecraft/src/')):continue
        for s in ss:ri[s].add(n)
    votes=collections.defaultdict(collections.Counter)
    for s,ns in pi.items():
        if len(ns)==1 and len(ri.get(s,[]))==1:votes[next(iter(ns))][next(iter(ri[s]))]+=1
    for p,vs in sorted(votes.items(),key=lambda x:-max(x[1].values())):
        if p in CM:continue
        win=vs.most_common();r,num=win[0];a,b=PS[p],rs[r];ratio=len(a&b)/max(1,min(len(a),len(b)))
        if (num>=2 or (num==1 and a==b)) and ratio>=.65 and (len(win)==1 or num>win[1][1]*2):
            add(p,r,kind,'unique string anchors (%d), overlap %.3f'%(num,ratio))
    print('strings',kind,len(CM))

def pairtypes(a,b,kind,reason):
    aa=re.findall(r'L([^;]+);',a);bb=re.findall(r'L([^;]+);',b)
    if len(aa)!=len(bb) or genericdesc(a,P)!=genericdesc(b,V if kind=='vanilla' else C):return
    for x,y in zip(aa,bb):
        if x in P and y in (V if kind=='vanilla' else C):add(x,y,kind,reason)

def matchmethods(pc,rc,ref):
    ps=collections.defaultdict(list);rs=collections.defaultdict(list);matches=[]
    for m in pc['methods']:ps[fp(m,P)].append(m)
    for m in rc['methods']:rs[fp(m,ref)].append(m)
    for key,pm in ps.items():
        if len(pm)==1 and len(rs.get(key,[]))==1:matches.append((pm[0],rs[key][0],True))
    # An unambiguous descriptor after known class mappings is additional evidence.
    matchedp={id(m[0]) for m in matches};matchedr={id(m[1]) for m in matches}
    def desc(d,names):return re.sub(r'L([^;]+);',lambda m:'L'+names.get(m[1],m[1])+';',d)
    pd=collections.defaultdict(list);rd=collections.defaultdict(list)
    for m in pc['methods']:
        if id(m) not in matchedp:pd[(desc(m['desc'],CM),m['access']&8,m['name'] if m['name'].startswith('<') else '')].append(m)
    for m in rc['methods']:
        if id(m) not in matchedr:rd[(desc(m['desc'],VAN_CLASSES if ref is V else {}),m['access']&8,m['name'] if m['name'].startswith('<') else '')].append(m)
    for key,pm in pd.items():
        if len(pm)==1 and len(rd.get(key,[]))==1:matches.append((pm[0],rd[key][0],False))
    return matches

for iteration in range(12):
    before=len(CM)
    for p,(r,kind) in list(PAIRS.items()):
        pc=P[p];ref=V if kind=='vanilla' else C;rc=ref[r]
        if pc.get('super') in P and rc.get('super') in ref:add(pc['super'],rc['super'],kind,'superclass of '+CM[p])
        if len(pc['interfaces'])==len(rc['interfaces']):
            for a,b in zip(pc['interfaces'],rc['interfaces']):
                if a in P and b in ref:add(a,b,kind,'interface of '+CM[p])
        for pm,rm,exact in matchmethods(pc,rc,ref):
            pairtypes(pm['desc'],rm['desc'],kind,'descriptor of '+CM[p]+'/'+rm['name'])
            name=VAN_METHODS.get(r+'/'+rm['name']+rm['desc'],rm['name']) if kind=='vanilla' else rm['name']
            if not obf(name) and not name.startswith('<'):MM[(p,pm['name'],pm['desc'])]=name
            if exact:
                for a,b in zip(pm['ins'],rm['ins']):
                    if len(a)>2 and a[1] in ('field','method') and len(b)>2 and b[1]==a[1]:
                        add(a[2],b[2],kind,'instruction reference from '+CM[p]+'/'+name)
                        pairtypes(a[4],b[4],kind,'instruction descriptor from '+CM[p]+'/'+name)
                        if a[1]=='field':
                            fn=VAN_FIELDS.get(b[2]+'/'+b[3],b[3]) if kind=='vanilla' else b[3]
                            if not obf(fn):FM[(a[2],a[3],a[4])]=fn
                        elif a[3]!='<init>':
                            mn=VAN_METHODS.get(b[2]+'/'+b[3]+b[4],b[3]) if kind=='vanilla' else b[3]
                            if not obf(mn):MM[(a[2],a[3],a[4])]=mn
                    elif len(a)>2 and a[1]=='type' and len(b)>2 and b[1]=='type':add(a[2],b[2],kind,'type instruction from '+CM[p]+'/'+name)
    print('propagation',iteration,len(CM),'methods',len(MM),'fields',len(FM))
    if before==len(CM):break

# Small anonymous/helper classes become distinguishable once neighboring types
# are named. Match whole method multisets, not just short isolated methods.
for iteration in range(8):
    known=set(CM.values()) | {n for n in P if not obf(n)}
    def graphfp(c,ref,ispreview):
        def typ(n):
            mapped=CM.get(n,n) if ispreview else VAN_CLASSES.get(n,n) if ref is V else n
            return mapped if mapped in known or n not in ref else '@'
        def dt(d):return re.sub(r'L([^;]+);',lambda m:'L'+typ(m[1])+';',d)
        methods=[]
        for m in c['methods']:
            ins=[]
            for i in m['ins']:
                a=list(i)
                if len(a)>2 and a[1] in ('field','method'):a[2]=typ(a[2]);a[3]='<init>' if a[3]=='<init>' else '@';a[4]=dt(a[4])
                elif len(a)>2 and a[1]=='type':a[2]=typ(a[2])
                elif len(a)>2 and a[1]=='jump':a=a[:2]
                elif a[0]=='const' and isinstance(a[1],dict) and 'type' in a[1]:a[1]={'type':dt(a[1]['type'])}
                elif len(a)==1 and a[0] in (11,12,13):a=['const',float(a[0]-11)]
                elif len(a)==1 and a[0] in (14,15):a=['const',float(a[0]-14)]
                elif len(a)==1 and a[0] in (9,10):a=['const',a[0]-9]
                ins.append(a)
            methods.append(json.dumps([dt(m['desc']),ins],sort_keys=True))
        return json.dumps([typ(c.get('super')),sorted(typ(i) for i in c['interfaces']),sorted(methods)],sort_keys=True)
    pi=collections.defaultdict(list);ri=collections.defaultdict(list)
    for p,c in P.items():
        if p not in CM and obf(p) and not table(c):pi[graphfp(c,P,True)].append(p)
    for ref,kind in [(V,'vanilla'),(C,'cbz')]:
        for r,c in ref.items():
            target=VAN_CLASSES.get(r,r) if kind=='vanilla' else r
            if target in CM.values() or kind=='cbz' and not r.startswith('com/cheatbreaker/'):continue
            ri[graphfp(c,ref,False)].append((r,kind))
    before=len(CM)
    for key,ps in pi.items():
        if len(ps)==1 and len(ri.get(key,[]))==1:
            r,kind=ri[key][0];add(ps[0],r,kind,'unique whole-class fingerprint with named neighbor types')
    print('whole-class graph',iteration,len(CM))
    if before==len(CM):break

# Match fields by already mapped descriptors, then normalized access sites.
def mappeddesc(d,names):return re.sub(r'L([^;]+);',lambda m:'L'+names.get(m[1],m[1])+';',d)
for p,(r,kind) in PAIRS.items():
    ref=V if kind=='vanilla' else C;pc=P[p];rc=ref[r];pd=collections.defaultdict(list);rd=collections.defaultdict(list)
    for f in pc['fields']:
        if (p,f['name'],f['desc']) not in FM:pd[(mappeddesc(f['desc'],CM),f['access']&24)].append(f)
    used={FM.get((p,f['name'],f['desc'])) for f in pc['fields']}
    for f in rc['fields']:
        name=VAN_FIELDS.get(r+'/'+f['name'],f['name']) if kind=='vanilla' else f['name']
        if name not in used:rd[(mappeddesc(f['desc'],VAN_CLASSES if kind=='vanilla' else {}),f['access']&24)].append(f)
    for key,fs in pd.items():
        if len(fs)==1 and len(rd.get(key,[]))==1:
            f=fs[0];rf=rd[key][0];name=VAN_FIELDS.get(r+'/'+rf['name'],rf['name']) if kind=='vanilla' else rf['name']
            if not obf(name):FM[(p,f['name'],f['desc'])]=name

# Preserve obfuscated identifiers for unresolved classes until separately classified.
lines=[]
for p,n in sorted(CM.items()):lines.append('\t'.join(['CLASS',p,n]))
for key,n in sorted(FM.items()):lines.append('\t'.join(['FIELD',*key,n]))
for key,n in sorted(MM.items()):lines.append('\t'.join(['METHOD',*key,n]))
(ROOT/'recovery/names.tsv').write_text('\n'.join(lines)+'\n','utf-8')
(ROOT/'recovery/class-evidence.json').write_text(json.dumps({p:{'name':CM[p],'reference':PAIRS.get(p),'evidence':E[p]} for p in sorted(CM)},indent=2,ensure_ascii=False),'utf-8')
unresolved=[{'original':p,'super':c.get('super'),'interfaces':c['interfaces'],'methods':len(c['methods']),'fields':len(c['fields']),'strings':sorted(PS[p])[:80]} for p,c in P.items() if obf(p) and p not in CM]
(ROOT/'recovery/unresolved-classes.json').write_text(json.dumps(unresolved,indent=2,ensure_ascii=False),'utf-8')
print('FINAL',len(CM),len(FM),len(MM),'unresolved',len(unresolved))
