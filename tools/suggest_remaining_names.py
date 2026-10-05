"""Suggest reference identities using complete class method fingerprints."""
import pathlib,json,re,collections,difflib
ROOT=pathlib.Path(__file__).resolve().parents[1]
N={c['name']:c for c in json.loads((ROOT/'.target/current-source-symbols.json').read_text('utf-8'))}
R={}
for ref in ['reference-exceptions','netty-exceptions','jlayer-exceptions','websocket-exceptions','log4j-exceptions','json-exceptions','junit-3.8.2','slf4j-api-1.7.25','vecmath-1.5.2','native-lib-loader-2.3.4','junixsocket-era-exceptions','native-lib-2.0.3','native-lib-2.1.4','word-wrap-0.1.6','word-wrap-0.1.9','word-wrap-0.1.11']:
    R.update({c['name']:c for c in json.loads((ROOT/('.target/'+ref+'.json')).read_text('utf-8'))})
def desc(d):return re.sub(r'L[^;]+;', 'L@;',d)
def fp(m):
    out=[]
    for i in m['ins']:
        a=list(i)
        if len(a)>2 and a[1] in ('field','method'):a[2]='@';a[3]='<init>' if a[3]=='<init>' else '@';a[4]=desc(a[4])
        elif len(a)>2 and a[1]=='type':a[2]='@' if not a[2].startswith('java/') else a[2]
        elif len(a)>2 and a[1]=='jump':a=a[:2]
        elif a[0] in (9,10):a=['const',a[0]-9]
        elif a[0] in (11,12,13):a=['const',float(a[0]-11)]
        elif a[0] in (14,15):a=['const',float(a[0]-14)]
        elif a[0]=='const' and isinstance(a[1],dict) and 'type' in a[1]:a[1]={'type':desc(a[1]['type'])}
        out.append(json.dumps(a,sort_keys=True))
    return (desc(m['desc']),tuple(out))
index=collections.defaultdict(set);rf={};pf={}
for n,c in R.items():
    if n in N:continue
    rf[n]=[fp(m) for m in c['methods']]
    for key in rf[n]:
        if len(key[1])>=6:index[key].add(n)
suggestions=[]
for n,c in N.items():
    if not n.startswith('recovered/unidentified/'):continue
    keys=[fp(m)for m in c['methods']];votes=collections.Counter()
    for k in keys:
        if len(k[1])>=6:
            for rn in index[k]:votes[rn]+=1
    scored=[]
    for rn,v in votes.most_common(15):
        ref=R[rn]
        if c['super'] in N and c['super']!=ref['super']:continue
        if c['interfaces'] and all(x in N or x.startswith('java/') for x in c['interfaces']) and set(c['interfaces'])!=set(ref['interfaces']):continue
        a=collections.Counter(keys);b=collections.Counter(rf[rn]);matched=sum((a&b).values());score=2*matched/(len(keys)+len(rf[rn]))
        if score>=0.65 and (matched>=2 or score==1):scored.append((score,matched,rn))
    scored.sort(reverse=True)
    if scored and (len(scored)==1 or scored[0][0]>scored[1][0]):suggestions.append({'source':n,'reference':scored[0][2],'exact_method_fraction':scored[0][0],'exact_methods':scored[0][1]})
(ROOT/'recovery/remaining-name-suggestions.json').write_text(json.dumps(suggestions,indent=2),'utf-8')
print('Unambiguous reference suggestions',len(suggestions));print('\n'.join(str(x)for x in suggestions[:25]))
