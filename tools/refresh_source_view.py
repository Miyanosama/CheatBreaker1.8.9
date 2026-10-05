"""Materialize corrected decompiler output without changing the authoritative archive."""
import collections,json,pathlib,re,shutil
ROOT=pathlib.Path(__file__).resolve().parents[1]
def mask_java(s):
    pattern=re.compile(r'//[^\n]*|/\*.*?\*/|"(?:\\.|[^"\\])*"|\'(?:\\.|[^\'\\])*\'',re.S)
    return pattern.sub(lambda m:''.join('\n' if c=='\n' else ' ' for c in m[0]),s)
N=json.loads((ROOT/'.target/source-repaired.json').read_text('utf-8'));tables=collections.defaultdict(dict)
for c in N:
    for m in c['methods']:
        if m['name']!='<clinit>':continue
        ins=m['ins']
        for i,v in enumerate(ins):
            if v!=[79] or i<4:continue
            a,b,call,num=ins[i-4:i]
            if len(a)>2 and a[:2]==[178,'field'] and a[4]=='[I' and len(b)>2 and b[:2]==[178,'field'] and len(call)>2 and call[1]=='method' and call[3]=='ordinal' and num[0]=='const':tables[(a[2],a[3])][num[1]]=b[3]
main=ROOT/'src/main/java';backup=ROOT/'.target/source-before-structural-repairs'
if not backup.exists():shutil.copytree(main,backup)
repairs=[];choices={};failed=[]
for p in (ROOT/'.target/unique-vf').rglob('*.java'):
    rel=p.relative_to(ROOT/'.target/unique-vf');s=p.read_text('utf-8')
    s=re.sub(r'(?m)^[ \t]*public\s+[^\n;=]+\s+__junk\d+\s*;[^\n]*\n?','',s)
    pattern=re.compile(r'switch\s*\(\s*<unrepresentable>\.([\w$]+)\[(.*?)\.ordinal\(\)\]\s*\)\s*\{')
    changes=[];owner=rel.with_suffix('').as_posix()
    for match in pattern.finditer(s):
        field,expr=match[1],match[2];candidates=[t for (o,f),t in tables.items() if f==field and o.startswith(owner+'$')]
        if len(candidates)!=1:candidates=[t for (o,f),t in tables.items() if f==field]
        if not candidates:continue
        if any(t!=candidates[0] for t in candidates):continue
        table=candidates[0];mask=mask_java(s);brace=match.end()-1;depth=1;end=brace+1
        while end<len(mask) and depth:
            if mask[end]=='{':depth+=1
            elif mask[end]=='}':depth-=1
            end+=1
        body=s[brace+1:end-1];bodymask=mask[brace+1:end-1];cases=[]
        for case in re.finditer(r'\bcase\s+(\d+)\s*:',bodymask):
            pre=bodymask[:case.start()]
            if pre.count('{')!=pre.count('}'):continue
            if int(case[1]) not in table:raise RuntimeError('Missing enum case '+str(rel))
            cases.append((case.start(),case.end(),'case '+table[int(case[1])]+':'))
        for a,b,v in reversed(cases):body=body[:a]+v+body[b:]
        changes.append((match.start(),end,'switch ('+expr+') {'+body+'}'));repairs.append({'source':rel.as_posix(),'field':field,'cases':table})
    for a,b,v in reversed(changes):s=s[:a]+v+s[b:]
    if '<unrepresentable>' in s or "$VF: Couldn't" in s:
        old=backup/rel
        if old.exists():s=old.read_text('utf-8');choices[rel.as_posix()]='previous complete decompilation'
        else:failed.append(rel.as_posix());continue
    else:choices[rel.as_posix()]='Vineflower with complete classpath and bridge repairs'
    # Object has no constructor side effects and cannot observe captured fields.
    # Dropping this misplaced explicit call restores Java's implicit Object call.
    s=re.sub(r'(?m)^[ \t]*super\(\);\s*\n','',s)
    s=s.replace('recovered.unidentified.UnidentifiedClass3556','com.cheatbreaker.client.event.EventBus').replace('UnidentifiedClass3556','EventBus')
    s=s.replace('EventBus.Event','EventBus$Event')
    out=main/rel;out.parent.mkdir(parents=True,exist_ok=True);out.write_text(s,'utf-8')
# Remove old class aliases only after their replacement source exists.
aliases=json.loads((ROOT/'recovery/source-bridge-repairs.json').read_text('utf-8'))[0]
for old,new in aliases.items():
    p=main/(old+'.java');dest=main/(new+'.java')
    if p.exists() and dest.exists():p.unlink()
for p in main.rglob('*.java'):
    s=p.read_text('utf-8')
    for old,new in aliases.items():
        s=s.replace(old.replace('/','.'),new.replace('/','.'))
        if old.rsplit('/',1)[-1] not in ('UnidentifiedClass3556',):s=s.replace(old.rsplit('/',1)[-1].replace('$','.'),new.rsplit('/',1)[-1])
    s=s.replace('EventBus.Event','EventBus$Event');p.write_text(s,'utf-8')
(ROOT/'recovery/source-selection.json').write_text(json.dumps(choices,indent=2),'utf-8')
(ROOT/'recovery/structural-enum-repairs.json').write_text(json.dumps(repairs,indent=2),'utf-8')
print('Selected',len(choices),'files; enum switches',len(repairs),'unselected',failed)
