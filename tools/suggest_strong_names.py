"""Match unknown classes while retaining known allocation and enum identities."""
import collections, csv, io, json, re, zipfile
from suggest_remaining_names import ROOT, N, R
field_map={}
with zipfile.ZipFile(ROOT/'.target/tools/mcp-stable-22.zip') as z:
    names={r['searge']:r['name']for r in csv.DictReader(io.StringIO(z.read('fields.csv').decode()))}
with zipfile.ZipFile(ROOT/'.target/tools/mcp-1.8.9-srg.zip') as z:
    lines=[line.split()for line in z.read('joined.srg').decode().splitlines()]
    classes={a[1]:a[2]for a in lines if a[0]=='CL:'}
    for a in lines:
        if a[0]!='FD:':continue
        owner,member=a[1].rsplit('/',1);target=a[2].rsplit('/',1)[1]
        field_map[(classes.get(owner,owner),member)]=names.get(target,target)
def descriptor(s,owner):
    return re.sub('L([^;]+);',lambda m:'L'+('@'if m[1]==owner or m[1]not in N and not m[1].startswith(('java/','javax/','com/google/'))else m[1])+';',s)
def fingerprint(m,owner,reference):
    out=[]
    for i in m['ins']:
        a=list(i)
        if len(a)>2 and a[1]in ('field','method'):
            o,name,d=a[2:5]
            a[2]=o if o!=owner and o in N and not o.startswith('recovered/')else '@'
            a[4]=descriptor(d,owner)
            if a[1]=='field':
                mapped=field_map.get((o,name),name)if reference else name
                enum=(N.get(o)or R.get(o)or {}).get('super')=='java/lang/Enum'
                a[3]=mapped if enum or o.startswith(('java/','javax/'))else '@'
            else:a[3]=name if name=='<init>'or o.startswith(('java/','javax/'))else '@'
        elif len(a)>2 and a[1]=='type':a[2]=a[2]if a[2]!=owner and(a[2]in N and not a[2].startswith('recovered/')or a[2].startswith('java/'))else '@'
        elif len(a)>2 and a[1]=='jump':a=a[:2]
        elif a[0]in(9,10):a=['const',a[0]-9]
        elif a[0]in(11,12,13):a=['const',float(a[0]-11)]
        elif a[0]in(14,15):a=['const',float(a[0]-14)]
        elif a[0]=='const'and isinstance(a[1],dict)and'type'in a[1]:a[1]={'type':descriptor(a[1]['type'],owner)}
        out.append(json.dumps(a,sort_keys=True))
    return descriptor(m['desc'],owner),tuple(out)
index=collections.defaultdict(set);refprints={}
for owner,c in R.items():
    if owner in N:continue
    prints=collections.Counter(fingerprint(m,owner,True)for m in c['methods']);refprints[owner]=prints
    for k in prints:
        if len(k[1])>=6:index[k].add(owner)
out=[]
for owner,c in N.items():
    if not owner.startswith('recovered/unidentified/'):continue
    prints=collections.Counter(fingerprint(m,owner,False)for m in c['methods']);votes=collections.Counter()
    for k,count in prints.items():
        for target in index[k]:votes[target]+=count
    candidates=[]
    for target,vote in votes.items():
        rc=R[target]
        if c['super']!=rc['super']:continue
        if set(c['interfaces'])!=set(rc['interfaces']):continue
        matches=sum((prints&refprints[target]).values());fraction=2*matches/(sum(prints.values())+sum(refprints[target].values()))
        if fraction>=.65 and matches>=2:candidates.append((fraction,matches,target))
    candidates.sort(reverse=True)
    if candidates and(len(candidates)==1 or candidates[0][0]>candidates[1][0]):
        score,matches,target=candidates[0];out.append({'source':owner,'reference':target,'fraction':score,'matches':matches})
targets=collections.Counter(x['reference']for x in out);out=[x for x in out if targets[x['reference']]==1]
(ROOT/'recovery/strong-name-suggestions.json').write_text(json.dumps(out,indent=2),'utf-8')
print('Strong reference suggestions:',len(out))
for x in out:print(x)
