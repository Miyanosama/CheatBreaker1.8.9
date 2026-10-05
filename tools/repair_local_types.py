"""Recover local types from bytecode descriptors and explicit decompiler casts."""
import collections,json,pathlib,re
ROOT=pathlib.Path(__file__).resolve().parents[1];N=json.loads((ROOT/'.target/source-repaired.json').read_text('utf-8'))
fields=collections.defaultdict(set);methods=collections.defaultdict(set)
def java(d):
    if d.startswith('['):return java(d[1:])+'[]'
    if d.startswith('L'):return d[1:-1].replace('/','.').replace('$','.')
    return {'I':'int','J':'long','Z':'boolean','F':'float','D':'double','B':'byte','S':'short','C':'char','V':'void'}.get(d,'java.lang.Object')
for c in N:
    for f in c['fields']:fields[f['name']].add(java(f['desc']))
    for m in c['methods']:methods[m['name']].add(java(m['desc'].split(')',1)[1]))
audit=[]
for p in (ROOT/'src/main/java').rglob('*.java'):
    s=p.read_text('utf-8');old=s
    def replace(m):
        var,expr=m[1],m[2];typ=None
        cast=re.match(r'\(([^()]+)\)',expr)
        if cast and re.fullmatch(r'[\w.]+(?:\[\])*',cast[1]):typ=cast[1]
        if not typ:
            call=re.search(r'(\w+)\([^()]*\)$',expr)
            if call and len(methods[call[1]])==1:typ=next(iter(methods[call[1]]))
        if not typ:
            f=re.fullmatch(r'(?:this\.)?(\w+)',expr)
            if f and len(fields[f[1]])==1:typ=next(iter(fields[f[1]]))
        if typ and typ not in ('java.lang.Object','void'):
            audit.append({'source':p.relative_to(ROOT).as_posix(),'variable':var,'type':typ,'initializer':expr});return typ+' '+var+' = '+expr+';'
        return m[0]
    s=re.sub(r'\bObject (\w+) = ([^;\n]+);',replace,s)
    s=s.replace('Iterable<int>','Iterable<Integer>').replace('Iterable<boolean>','Iterable<Boolean>')
    # Fully qualified array casts already present in erased toArray bytecode.
    s=re.sub(r'(?<![\w.)])\b((?:\w+\.)*\w+\.toArray\(new ([\w.]+)\[[^\]]*\]\))',lambda m:'('+m[2]+'[])'+m[1],s)
    # Static fields inside non-static inner classes must be compile-time constants.
    if s!=old:p.write_text(s,'utf-8')
(ROOT/'recovery/local-type-repairs.json').write_text(json.dumps(audit,indent=2),'utf-8');print('Recovered local type declarations',len(audit))
