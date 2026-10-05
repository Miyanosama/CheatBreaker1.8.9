"""Reconstruct enum switch syntax from the exact synthetic bytecode tables."""
import collections,json,pathlib,re
def mask_java(s):
    pattern=re.compile(r'//[^\n]*|/\*.*?\*/|"(?:\\.|[^"\\])*"|\'(?:\\.|[^\'\\])*\'',re.S)
    return pattern.sub(lambda m:''.join('\n' if c=='\n' else ' ' for c in m[0]),s)
ROOT=pathlib.Path(__file__).resolve().parents[1]
N=json.loads((ROOT/'.target/named.json').read_text('utf-8'));tables=collections.defaultdict(dict)
for c in N:
    for m in c['methods']:
        if m['name']!='<clinit>':continue
        ins=m['ins']
        for i,v in enumerate(ins):
            if v!=[79] or i<4:continue
            a,b,call,num=ins[i-4:i]
            if len(a)>2 and a[:2]==[178,'field'] and a[4]=='[I' and len(b)>2 and b[:2]==[178,'field'] and len(call)>2 and call[1]=='method' and call[3]=='ordinal' and num[0]=='const' and isinstance(num[1],int):
                tables[(a[2],a[3])][num[1]]=b[3]
paths=json.loads((ROOT/'.target/compile-error-files.json').read_text('utf-8'))
repairs=[]
for name in paths:
    p=pathlib.Path(name);rel=p.relative_to(ROOT/'src/main/java');vp=ROOT/'.target/final-vf'/rel
    if not vp.exists():continue
    s=vp.read_text('utf-8');s=re.sub(r'(?m)^[ \t]*public\s+[^\n;=]+\s+__junk\d+\s*;[^\n]*\n?','',s)
    pattern=re.compile(r'switch\s*\(\s*<unrepresentable>\.([\w$]+)\[(.*?)\.ordinal\(\)\]\s*\)\s*\{')
    replacements=[];owner=str(rel.with_suffix('')).replace('\\','/')
    for match in pattern.finditer(s):
        field,expr=match[1],match[2];candidates=[(o,t) for (o,f),t in tables.items() if f==field and o.startswith(owner+'$')]
        if len(candidates)!=1:candidates=[(o,t) for (o,f),t in tables.items() if f==field]
        if len(candidates)!=1:continue
        table=candidates[0][1];mask=mask_java(s);brace=match.end()-1;depth=1;end=brace+1
        while end<len(mask) and depth:
            if mask[end]=='{':depth+=1
            elif mask[end]=='}':depth-=1
            end+=1
        body=s[brace+1:end-1];bodymask=mask[brace+1:end-1];casechanges=[]
        for case in re.finditer(r'\bcase\s+(\d+)\s*:',bodymask):
            pre=bodymask[:case.start()]
            if pre.count('{')!=pre.count('}'):continue
            num=int(case[1])
            if num not in table:raise RuntimeError('Missing enum switch entry '+field+' '+str(num))
            casechanges.append((case.start(),case.end(),'case '+table[num]+':'))
        for a,b,v in reversed(casechanges):body=body[:a]+v+body[b:]
        replacements.append((match.start(),end,'switch ('+expr+') {'+body+'}'))
        repairs.append({'source':str(rel).replace('\\','/'),'field':field,'bytecode_owner':candidates[0][0],'case_mapping':table})
    for a,b,v in reversed(replacements):s=s[:a]+v+s[b:]
    if replacements and '<unrepresentable>' not in s and "$VF: Couldn't" not in s:p.write_text(s,'utf-8')
(ROOT/'recovery/enum-switch-repairs.json').write_text(json.dumps(repairs,indent=2),'utf-8')
print('Reconstructed',len(repairs),'enum switches from bytecode tables')
