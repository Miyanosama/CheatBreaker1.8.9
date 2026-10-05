"""Resolve naming collisions and give unresolved symbols stable, explicit names."""
import collections, csv, hashlib, json, pathlib, re, zipfile
ROOT=pathlib.Path(__file__).resolve().parents[1]
def load(n):return {c['name']:c for c in json.loads((ROOT/'.target'/n).read_text('utf-8'))}
P=load('preview.json');C=load('cbz.json');V=load('optifine-vanilla.json')
for n in ['log4j.json','Java-WebSocket-1.5.2.json','jlayer.json','junixsocket.json','junit-3.8.1.json','vecmath-1.5.2.json','json-20160810.json','slf4j-api-1.7.25.json','native-lib-loader-2.3.4.json']:V.update(load(n))
CM={};FM={};MM={};VM={};VF={};VC={};evidence=json.loads((ROOT/'recovery/class-evidence.json').read_text('utf-8'))
for l in (ROOT/'recovery/names.tsv').read_text('utf-8').splitlines():
    a=l.split('\t')
    if a[0]=='CLASS':CM[a[1]]=a[2]
    elif a[0]=='FIELD':FM[tuple(a[1:4])]=a[4]
    elif a[0]=='METHOD':MM[tuple(a[1:4])]=a[4]
with zipfile.ZipFile(ROOT/'.target/tools/mcp-stable-22.zip') as z:
    names={}
    for n in ['fields.csv','methods.csv']:names.update({r['searge']:r['name'] for r in csv.DictReader(z.read(n).decode().splitlines())})
with zipfile.ZipFile(ROOT/'.target/tools/mcp-1.8.9-srg.zip') as z:
    for l in z.read('joined.srg').decode().splitlines():
        a=l.split()
        if a[0]=='CL:':VC[a[1]]=a[2]
        elif a[0]=='FD:':VF[a[1]]=names.get(a[2].rsplit('/',1)[-1],a[2].rsplit('/',1)[-1])
        elif a[0]=='MD:':VM[a[1]+a[2]]=names.get(a[3].rsplit('/',1)[-1],a[3].rsplit('/',1)[-1])
for n in load('optifine.json'):
    if '/' not in n:VC[n]='net/minecraft/src/'+n
def obf(s):return bool(re.fullmatch('[Il]{20,}',s))
def desc(d,cm):return re.sub(r'L([^;]+);',lambda m:'L'+cm.get(m[1],m[1])+';',d)
def declared(owner,name,d,kind,visited=None):
    if visited is None:visited=set()
    if owner in visited:return owner
    visited.add(owner);c=P.get(owner)
    if not c:return owner
    if any(x['name']==name and x['desc']==d for x in c[kind]):return owner
    for parent in [c.get('super'),*c['interfaces']]:
        if parent in P:
            found=declared(parent,name,d,kind,visited)
            if any(x['name']==name and x['desc']==d for x in P.get(found,{}).get(kind,[])):return found
    return owner

# Normalize inherited references to the class that actually declares a symbol.
for maps,kind in [(FM,'fields'),(MM,'methods')]:
    votes=collections.defaultdict(collections.Counter)
    for (o,n,d),v in maps.items():votes[(declared(o,n,d,kind),n,d)][v]+=1
    maps.clear()
    for key,v in votes.items():maps[key]=v.most_common(1)[0][0]

def semantic_fp(m,cm,fm,reference=False):
    out=[]
    for i in m['ins']:
        a=list(i)
        if len(a)>2 and a[1] in ('field','method'):
            o,n,d=a[2:5]
            if a[1]=='field':a[3]=VF.get(o+'/'+n,n) if reference else fm.get((declared(o,n,d,'fields'),n,d),n)
            else:a[3]='<init>' if n=='<init>' else '@'
            a[2]=cm.get(o,o);a[4]=desc(d,cm)
        elif len(a)>2 and a[1]=='type':a[2]=cm.get(a[2],desc(a[2],cm))
        elif len(a)>2 and a[1]=='jump':a=a[:2]
        elif a[0]=='const' and isinstance(a[1],dict) and 'type' in a[1]:a[1]={'type':desc(a[1]['type'],cm)}
        out.append(a)
    return json.dumps([desc(m['desc'],cm),out],sort_keys=True)
corrected=0
for o,e in evidence.items():
    if not e.get('reference'):continue
    r,kind=e['reference'];ref=V if kind=='vanilla' else C
    if r not in ref:continue
    pm=collections.defaultdict(list);rm=collections.defaultdict(list)
    for m in P[o]['methods']:pm[semantic_fp(m,CM,FM)].append(m)
    for m in ref[r]['methods']:rm[semantic_fp(m,VC if kind=='vanilla' else {},{},kind=='vanilla')].append(m)
    for key,ms in pm.items():
        if len(ms)==1 and len(rm.get(key,[]))==1:
            a,b=ms[0],rm[key][0];n=VM.get(r+'/'+b['name']+b['desc'],b['name']) if kind=='vanilla' else b['name']
            if not obf(n) and not n.startswith('<'):
                k=(o,a['name'],a['desc'])
                if MM.get(k)!=n:corrected+=1
                MM[k]=n

# Every missing class receives an honest placeholder, never an asserted original name.
fallback=[];tableindex=0
for index,o in enumerate(sorted(P)):
    if o in CM or not obf(o):continue
    c=P[o]
    if len(c['methods'])==1 and c['methods'][0]['name']=='<clinit>' and len(c['fields'])==1 and c['fields'][0]['desc']=='[Ljava/lang/String;':
        tableindex+=1;n='recovered/strings/StringTable%02d'%tableindex;role='string-table'
    else:
        prefix='UnidentifiedEnum' if c['access']&16384 else 'UnidentifiedInterface' if c['access']&512 else 'UnidentifiedClass'
        n='recovered/unidentified/%s%04d'%(prefix,index);role='unidentified'
    CM[o]=n;fallback.append({'original':o,'name':n,'role':role});evidence[o]={'name':n,'reference':None,'evidence':role+'; original name unknown'}

# Preserve virtual dispatch: overrides and covariant bridges share a component.
parent={};methods={}
def find(x):
    parent.setdefault(x,x)
    if parent[x]!=x:parent[x]=find(parent[x])
    return parent[x]
def union(a,b):
    a,b=find(a),find(b)
    if a!=b:parent[max(a,b)]=min(a,b)
for o,c in P.items():
    for m in c['methods']:
        k=(o,m['name'],m['desc']);methods[k]=m;find(k)
for k,m in methods.items():
    o,n,d=k
    if n.startswith('<') or m['access']&10:continue
    args=d.split(')')[0]+')';todo=[o];seen=set()
    while todo:
        t=todo.pop()
        if t in seen or t not in P:continue
        seen.add(t)
        for m2 in P[t]['methods']:
            if m2['name']==n and m2['desc'].split(')')[0]+')'==args and not m2['access']&10:union(k,(t,n,m2['desc']))
        todo.extend([P[t].get('super'),*P[t]['interfaces']])
components=collections.defaultdict(list)
for k in methods:components[find(k)].append(k)
component_names={};conflicts=[]
for idx,(root,ks) in enumerate(sorted(components.items())):
    if root[1].startswith('<'):continue
    original=next((k[1] for k in ks if not obf(k[1])),None)
    known=collections.Counter(MM[k] for k in ks if k in MM and not obf(MM[k]))
    if len(known)>1:conflicts.append({'symbols':ks,'candidates':dict(known),'resolution':'majority within override family'})
    n=original or (known.most_common(1)[0][0] if known else 'method_%05d'%idx)
    component_names[root]=n

# Java cannot declare two methods with identical name and parameter list.
for iteration in range(4):
    clashes=collections.defaultdict(set)
    for k in methods:
        if k[1].startswith('<') or methods[k]['access']&4096:continue
        root=find(k);clashes[(k[0],component_names[root],k[2].split(')')[0])].add(root)
    changed=False
    for key,roots in clashes.items():
        if len(roots)>1:
            for root in sorted(roots)[1:]:
                component_names[root]='method_'+hashlib.sha256(repr(root).encode()).hexdigest()[:10];changed=True
            conflicts.append({'collision':key,'symbols':[list(r) for r in sorted(roots)],'resolution':'stable unique fallback'})
    if not changed:break
MM={k:component_names[find(k)] for k in methods if not k[1].startswith('<')}

# Fields have no overloads in Java; reconcile ambiguous mappings explicitly.
for o,c in P.items():
    taken=set()
    for idx,f in enumerate(sorted(c['fields'],key=lambda f:(f['name'],f['desc']))):
        k=(o,f['name'],f['desc']);n=f['name'] if not obf(f['name']) else FM.get(k,'field_%04d'%idx)
        if n in taken:
            conflicts.append({'field':k,'candidate':n,'resolution':'stable unique fallback'});n='field_%04d'%idx
        while n in taken:n+='_'
        FM[k]=n;taken.add(n)

lines=['\t'.join(['CLASS',o,n]) for o,n in sorted(CM.items())]
lines.extend('\t'.join(['FIELD',*k,n]) for k,n in sorted(FM.items()))
lines.extend('\t'.join(['METHOD',*k,n]) for k,n in sorted(MM.items()))
(ROOT/'recovery/final-names.tsv').write_text('\n'.join(lines)+'\n','utf-8')
(ROOT/'recovery/final-class-evidence.json').write_text(json.dumps(evidence,indent=2,ensure_ascii=False),'utf-8')
(ROOT/'recovery/fallback-names.json').write_text(json.dumps(fallback,indent=2),'utf-8')
(ROOT/'recovery/member-name-conflicts.json').write_text(json.dumps(conflicts,indent=2),'utf-8')
report={'input_class_count':len(P),'method_count':sum(len(c['methods']) for c in P.values()),'field_count':sum(len(c['fields']) for c in P.values()),'mapped_class_count':len(CM),'confirmed_or_semantic_class_count':len(evidence)-len(fallback),'fallback_class_count':len(fallback),'fallback_unidentified_class_count':sum(x['role']=='unidentified' for x in fallback),'string_tables':tableindex,'corrected_member_matches':corrected,'member_conflicts':len(conflicts),'readable_method_names':sum(not n.startswith('method_') for n in MM.values()),'placeholder_method_names':sum(n.startswith('method_') for n in MM.values()),'readable_field_names':sum(not n.startswith('field_') for n in FM.values()),'placeholder_field_names':sum(n.startswith('field_') for n in FM.values())}
(ROOT/'recovery/summary.json').write_text(json.dumps(report,indent=2),'utf-8');print(json.dumps(report,indent=2))
