"""Recover unique reference method identities within already identified classes."""
import collections,contextlib,io,json,pathlib,re,runpy,sys
ROOT=pathlib.Path(__file__).resolve().parents[1]
with contextlib.redirect_stdout(io.StringIO()):env=runpy.run_path(str(ROOT/'tools/suggest_remaining_names.py'))
N,R,fp=env['N'],env['R'],env['fp'];aliases=json.loads((ROOT/'recovery/confirmed-source-class-aliases.json').read_text('utf-8'))
def descriptor(d):return re.sub(r'L([^;]+);',lambda m:'L'+aliases.get(m[1],m[1])+';',d)
def strictfp(m):
    d,ins=fp(m);out=[]
    for item,original in zip(ins,m['ins']):
        a=json.loads(item)
        if len(original)>4 and original[1]=='method'and original[2].startswith(('java/','javax/')):a[3]=original[3]
        out.append(json.dumps(a,sort_keys=True))
    return d,tuple(out)
renames={};audit=[]
for n,c in N.items():
    rn=aliases.get(n,n)
    if rn not in R:continue
    rc=R[rn];existing={(m['name'],descriptor(m['desc']))for m in c['methods']}
    for m in c['methods']:
        if not re.fullmatch(r'method_\d+',m['name']):continue
        candidates=[rm for rm in rc['methods']if descriptor(m['desc'])==rm['desc']and strictfp(m)==strictfp(rm)and len(m['ins'])>=4]
        if len(candidates)!=1:continue
        rm=candidates[0]
        if rm['name'].startswith('<')or(rm['name'],rm['desc'])in existing:continue
        renames[m['name']]=rm['name'];audit.append({'class':rn,'old':m['name'],'name':rm['name'],'descriptor':rm['desc'],'evidence':'unique same-class exact normalized method body and descriptor'})
groups=collections.Counter((x['class'],x['name'],x['descriptor'])for x in audit)
audit=[x for x in audit if groups[x['class'],x['name'],x['descriptor']]==1]
renames={x['old']:x['name']for x in audit}
(ROOT/'recovery/reference-method-restoration-candidates.json').write_text(json.dumps(audit,indent=2),'utf-8')
print('Unique method identities',len(renames))
if '--apply'not in sys.argv:sys.exit()
pat=re.compile(r'\b('+'|'.join(map(re.escape,renames))+r')\b')
for p in (ROOT/'src/main/java').rglob('*.java'):
    s=p.read_text('utf-8');masked=re.sub(r'//[^\n]*|/\*.*?\*/|"(?:\\.|[^"\\])*"|\'(?:\\.|[^\'\\])*\'',lambda m:' '*len(m[0]),s,flags=re.S)
    hits=list(pat.finditer(masked))
    for h in reversed(hits):s=s[:h.start()]+renames[h[0]]+s[h.end():]
    if hits:p.write_text(s,'utf-8')
prior=json.loads((ROOT/'recovery/reference-method-restoration.json').read_text('utf-8'))
byold={x['old']:x for x in prior}
byold.update({x['old']:x for x in audit})
(ROOT/'recovery/reference-method-restoration.json').write_text(json.dumps(list(byold.values()),indent=2),'utf-8')
