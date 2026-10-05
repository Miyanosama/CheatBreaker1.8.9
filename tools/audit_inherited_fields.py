"""Resolve each field against its original declaration before both renaming passes."""
import collections, json, pathlib, re
ROOT = pathlib.Path(__file__).resolve().parents[1]
def read(name): return json.loads((ROOT/name).read_text('utf-8'))
P={c['name']:c for c in read('.target/preview.json')}
I={c['name']:c for c in read('.target/source-input.json')}
S={c['name']:c for c in read('.target/source-repaired.json')}
cm={}; fm={}; mm={}
for line in (ROOT/'recovery/final-names.tsv').read_text('utf-8').splitlines():
    a=line.split('\t')
    if a[0]=='CLASS': cm[a[1]]=a[2]
    elif a[0]=='FIELD': fm[tuple(a[1:4])]=a[4]
    elif a[0]=='METHOD': mm[tuple(a[1:4])]=a[4]
aliases, bridges=read('recovery/source-bridge-repairs.json')
bridge_map={(x[0],x[1],x[2]):x[3]for x in bridges}
def desc(s): return re.sub('L([^;]+);',lambda m:'L'+cm.get(m[1],m[1])+';',s)
def resolve(owner,name,d,seen=None):
    seen=set()if seen is None else seen
    if owner in seen or owner not in P:return None
    seen.add(owner);c=P[owner]
    if any(f['name']==name and f['desc']==d for f in c['fields']):return owner
    for parent in [c['super']]+c['interfaces']:
        out=resolve(parent,name,d,seen)
        if out:return out
    return None
unique={}; counter=0
for owner,c in I.items():
    for f in c['fields']:
        name=f['name']
        if re.fullmatch('field_[0-9]+',name): name='recoveredField'+str(counter);counter+=1
        unique[(owner,f['name'],f['desc'])]=name
out=[]; allrefs=collections.defaultdict(lambda:collections.defaultdict(set))
for old,c in P.items():
    named=cm.get(old,old);new=aliases.get(named,named)
    if new not in S:continue
    methods={(m['name'],m['desc']):m for m in S[new]['methods']}
    for m in c['methods']:
        mn=mm.get((old,m['name'],m['desc']),m['name']);md=desc(m['desc'])
        mn=bridge_map.get((named,mn,md),mn)
        other=methods.get((mn,md))
        if not other:continue
        aa=[x for x in m['ins']if len(x)>4 and x[1]=='field']
        bb=[x for x in other['ins']if len(x)>4 and x[1]=='field']
        if len(aa)!=len(bb):continue
        for i,(a,b)in enumerate(zip(aa,bb)):
            decl=resolve(a[2],a[3],a[4])
            if not decl:continue
            key=(decl,a[3],a[4]);field=fm.get(key,a[3])
            expected=unique.get((cm.get(decl,decl),field,desc(a[4])),field)
            allrefs[(new,mn,md)][b[3]].add(expected)
            if expected!=b[3]:out.append({'class':new,'method':mn,'desc':md,'actual':b[3],
                'expected':expected,'declaration':aliases.get(cm.get(decl,decl),cm.get(decl,decl)),
                'reference_owner':b[2],'original_field':a[3],'field_index':i})
for x in out:x['ambiguous_in_method']=len(allrefs[(x['class'],x['method'],x['desc'])][x['actual']])>1
(ROOT/'.target/inherited-field-reference-audit.json').write_text(json.dumps(out,indent=2),'utf-8')
print('Mismatched field references:',len(out),'affected methods:',len({(x['class'],x['method'],x['desc'])for x in out}),
      'ambiguous references:',sum(x['ambiguous_in_method']for x in out))
for x in out[:25]:print(x)
