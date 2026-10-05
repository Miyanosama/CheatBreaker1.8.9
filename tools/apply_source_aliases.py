"""Apply reviewed source identities without regenerating decompiled methods."""
import json,pathlib,re,sys
ROOT=pathlib.Path(__file__).resolve().parents[1];SRC=ROOT/'src/main/java'
pending=json.loads(pathlib.Path(sys.argv[1]).read_text('utf-8'))
auditp=ROOT/'recovery/confirmed-source-class-aliases.json'
audit=json.loads(auditp.read_text('utf-8'))if auditp.exists()else{}
changed={old:new for old,new in pending.items()if old not in audit}
for old,new in changed.items():
    if not (SRC/(old+'.java')).exists():raise ValueError('Missing '+old)
    if (SRC/(new+'.java')).exists():raise ValueError('Collision '+new)
for old,new in changed.items():
    oldp=SRC/(old+'.java');newp=SRC/(new+'.java')
    if not oldp.exists():raise ValueError('Missing '+old)
    if newp.exists():raise ValueError('Collision '+new)
    oldsimple=old.rsplit('/',1)[-1];newsimple=new.rsplit('/',1)[-1]
    oldpackage=old.rsplit('/',1)[0].replace('/','.');newpackage=new.rsplit('/',1)[0].replace('/','.')
    for p in SRC.rglob('*.java'):
        s=p.read_text('utf-8');original=s
        # Existing source is explicit about placeholders in strings; preserve
        # literal labels and replace Java identifiers/imports outside literals.
        if oldsimple not in s:continue
        if p==oldp:s=s.replace('package '+oldpackage+';','package '+newpackage+';')
        s=s.replace(old.replace('/','.'),new.replace('/','.'))
        masked=re.sub(r'//[^\n]*|/\*.*?\*/|"(?:\\.|[^"\\])*"|\'(?:\\.|[^\'\\])*\'',lambda m:''.join('\n' if c=='\n'else' 'for c in m[0]),s,flags=re.S)
        hits=list(re.finditer(r'\b'+re.escape(oldsimple)+r'\b',masked))
        if hits and p!=oldp and ('package '+newpackage+';')not in s and ('import '+new.replace('/','.')+';')not in s:
            package=re.search(r'package [\w.]+;',s)
            s=s[:package.end()]+'\n\nimport '+new.replace('/','.')+';'+s[package.end():]
            masked=re.sub(r'//[^\n]*|/\*.*?\*/|"(?:\\.|[^"\\])*"|\'(?:\\.|[^\'\\])*\'',lambda m:' '*len(m[0]),s,flags=re.S)
            hits=list(re.finditer(r'\b'+re.escape(oldsimple)+r'\b',masked))
        for h in reversed(hits):s=s[:h.start()]+newsimple+s[h.end():]
        if s!=original:p.write_text(s,'utf-8')
    newp.parent.mkdir(parents=True,exist_ok=True);oldp.replace(newp);audit[old]=new
for p in SRC.rglob('*.java'):
    s=p.read_text('utf-8')
    if 'package recovered.unidentified;' in s:continue
    imports=['import recovered.unidentified.'+n+';' for n in set(re.findall(r'\bUnidentified(?:Class|Enum|Interface)\d+\b',s))if (SRC/'recovered/unidentified'/(n+'.java')).exists()and 'import recovered.unidentified.'+n+';'not in s]
    if imports:
        h=re.search(r'package [\w.]+;',s);s=s[:h.end()]+'\n\n'+'\n'.join(sorted(imports))+s[h.end():];p.write_text(s,'utf-8')
auditp.write_text(json.dumps(audit,indent=2),'utf-8')
print('Applied reviewed class identities',len(changed))
