"""Infer anonymous/switch helper identity from exact containing method bytecode."""
import collections, json
from suggest_remaining_names import ROOT, N, R, fp
votes=collections.defaultdict(collections.Counter)
evidence=collections.defaultdict(list)
for owner,c in N.items():
    if owner not in R or owner.startswith('recovered/'):continue
    ref=R[owner];index=collections.defaultdict(list)
    for method in ref['methods']:index[fp(method)].append(method)
    for method in c['methods']:
        matches=index[fp(method)]
        if not matches:continue
        for i,a in enumerate(method['ins']):
            if len(a)<3 or a[1]not in ('type','field','method')or not a[2].startswith('recovered/unidentified/'):continue
            candidates=set()
            for matched in matches:
                if len(matched['ins'])!=len(method['ins']):continue
                b=matched['ins'][i]
                if len(b)>2 and b[1]==a[1] and b[0]==a[0] and b[2]in R and b[2]not in N:candidates.add(b[2])
            if len(candidates)!=1:continue
            target=next(iter(candidates))
            # Require matching superclass and complete method fingerprint multiset.
            unknown=N[a[2]];rc=R[target]
            if unknown['super']!=rc['super']:continue
            uf=collections.Counter(fp(x)for x in unknown['methods']);rf=collections.Counter(fp(x)for x in rc['methods'])
            matched_count=sum((uf&rf).values())
            fraction=2*matched_count/(sum(uf.values())+sum(rf.values()))
            if fraction<.8:continue
            votes[a[2]][target]+=1
            evidence[(a[2],target)].append({'caller':owner,'method':method['name'],'reference_method':matched['name'],'instruction':i,'class_fraction':fraction})
out=[]
for source,counts in votes.items():
    if len(counts)!=1:continue
    target,count=counts.most_common(1)[0]
    out.append({'source':source,'reference':target,'votes':count,'evidence':evidence[(source,target)]})
targets=collections.Counter(x['reference']for x in out)
out=[x for x in out if targets[x['reference']]==1]
(ROOT/'recovery/context-name-suggestions.json').write_text(json.dumps(out,indent=2),'utf-8')
print('Unique context names:',len(out))
for x in out:print(x['source'], '=>',x['reference'],'votes',x['votes'])
