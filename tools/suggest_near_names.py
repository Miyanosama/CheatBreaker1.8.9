"""Read-only suggestions for modified reference classes; never apply names."""
import collections,contextlib,difflib,io,json,pathlib,runpy
ROOT=pathlib.Path(__file__).resolve().parents[1]
with contextlib.redirect_stdout(io.StringIO()):env=runpy.run_path(str(ROOT/'tools/suggest_remaining_names.py'))
N,R,fp=env['N'],env['R'],env['fp']
RF={n:[fp(m)for m in c['methods'] if len(m['ins'])>=4]for n,c in R.items()if n not in N}
index=collections.defaultdict(set)
for n,keys in RF.items():
    for d,ins in keys:
        for x in set(ins):
            if x.startswith('["const", "') and len(x)>13:index[x].add(n)
        if len(ins)>=6:index[(d,ins)].add(n)
result=[]
for n,c in N.items():
    if not n.startswith('recovered/unidentified/'):continue
    keys=[fp(m)for m in c['methods']if len(m['ins'])>=4];votes=collections.Counter()
    for k in keys:
        for rn in index[k]:votes[rn]+=8
        for x in set(k[1]):
            for rn in index[x]:votes[rn]+=1
    scored=[]
    for rn,_ in votes.most_common(30):
        rc=R[rn]
        if c['super']in N or c['super'].startswith('java/'):
            if c['super']!=rc['super']:continue
        candidates=[]
        for i,(d,ins)in enumerate(keys):
            for j,(rd,rins)in enumerate(RF[rn]):
                if d!=rd:continue
                if abs(len(ins)-len(rins))>max(len(ins),len(rins))*.25:continue
                if ins==rins:ratio=1.
                else:
                    sm=difflib.SequenceMatcher(None,ins,rins,autojunk=False)
                    if sm.quick_ratio()<.85:continue
                    ratio=sm.ratio()
                if ratio>=.85:candidates.append((ratio,i,j,len(ins)+len(rins)))
        used=set();matched=[]
        for ratio,i,j,weight in sorted(candidates,reverse=True):
            if ('n',i)in used or('r',j)in used:continue
            used.update([('n',i),('r',j)]);matched.append((ratio,weight))
        total=sum(len(x[1])for x in keys)+sum(len(x[1])for x in RF[rn])
        score=sum(r*w for r,w in matched)/total if total else 0
        if score>=.94 and any(r>=.97 and w>=120 for r,w in matched):scored.append((score,rn,matched))
    scored.sort(reverse=True)
    if scored and(len(scored)==1 or scored[0][0]-scored[1][0]>.04):
        result.append({'source':n,'reference':scored[0][1],'weighted_similarity':scored[0][0],'methods':scored[0][2]})
(ROOT/'recovery/near-name-suggestions.json').write_text(json.dumps(result,indent=2),'utf-8')
print('Near reference suggestions',len(result))
for x in result:print(x['source'].rsplit('/',1)[-1],x['reference'],round(x['weighted_similarity'],5))
