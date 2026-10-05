import json,pathlib,re
ROOT=pathlib.Path(__file__).resolve().parents[1]
paths=json.loads((ROOT/'.target/compile-error-files.json').read_text('utf-8'))
selection=json.loads((ROOT/'recovery/source-selection.json').read_text('utf-8'))
changed=[]
for name in paths:
    p=pathlib.Path(name)
    if not p.resolve().is_relative_to(ROOT/'src/main/java'):continue
    rel=p.relative_to(ROOT/'src/main/java');vp=ROOT/'.target/final-vf'/rel
    if not vp.exists():continue
    s=vp.read_text('utf-8');s=re.sub(r'(?m)^[ \t]*public\s+[^\n;=]+\s+__junk\d+\s*;[^\n]*\n?','',s)
    if '<unrepresentable>' in s or "$VF: Couldn't" in s or 'This method has failed' in s:continue
    if p.read_text('utf-8')==s:continue
    p.write_text(s,'utf-8');changed.append(str(rel));selection[str(rel).replace('\\','/')]='Vineflower 1.11.2 after CFR syntax diagnostics; source-only junk declarations removed'
(ROOT/'recovery/source-selection.json').write_text(json.dumps(selection,indent=2),'utf-8')
print('Replaced',len(changed),'sources with complete Vineflower alternatives')
