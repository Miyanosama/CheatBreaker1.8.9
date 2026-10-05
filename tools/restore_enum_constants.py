"""Restore enum names and declaration order from constructor operands."""
import collections,json,pathlib,re
ROOT=pathlib.Path(__file__).resolve().parents[1];src=ROOT/'src/main/java'
N=json.loads((ROOT/'.target/current-source-symbols.json').read_text('utf-8'))
def mask(s):
    return re.sub(r'//[^\n]*|/\*.*?\*/|"(?:\\.|[^"\\])*"|\'(?:\\.|[^\'\\])*\'',lambda m:''.join('\n' if ch=='\n' else ' ' for ch in m[0]),s,flags=re.S)
enums={};renames={}
for c in N:
    if c['super']!='java/lang/Enum':continue
    clinit=next((m for m in c['methods']if m['name']=='<clinit>'),None)
    if not clinit:continue
    ins=clinit['ins'];constants=[]
    for i,a in enumerate(ins):
        if len(a)<3 or a[:2]!=[187,'type'] or i+3>=len(ins):continue
        if a[2]!=c['name']and not any(x['name']==a[2]and x['super']==c['name']for x in N):continue
        if ins[i+1]!=[89] or ins[i+2][0]!='const' or ins[i+3][0]!='const':continue
        name,ordinal=ins[i+2][1],ins[i+3][1]
        if not isinstance(name,str)or not isinstance(ordinal,int):continue
        for b in ins[i+4:]:
            if len(b)>2 and b[:2]==[179,'field'] and b[2]==c['name'] and b[4]=='L'+c['name']+';':
                constants.append((ordinal,b[3],name));break
            if len(b)>2 and b[:2]==[187,'type'] and b[2]==c['name']:break
    if not constants or len({v[0]for v in constants})!=len(constants):continue
    constants.sort();enums[c['name']]=constants
    for ordinal,old,name in constants:
        if old.startswith('recoveredField')and old!=name and re.fullmatch(r'[A-Za-z_$][\w$]*',name):renames[old]=name
for p in src.rglob('*.java'):
    s=p.read_text('utf-8');masked=mask(s);changes=[]
    for m in re.finditer(r'\brecoveredField\d+\b',masked):
        if m[0]in renames:changes.append((m.start(),m.end(),renames[m[0]]))
    for a,b,value in reversed(changes):s=s[:a]+value+s[b:]
    if changes:p.write_text(s,'utf-8')
audit=[]
for owner,constants in enums.items():
    root=owner.split('$')[0];p=src/(owner+'.java')
    if not p.exists():p=src/(root+'.java')
    if not p.exists():p=src/(owner+'.java')
    if not p.exists():continue
    s=p.read_text('utf-8');masked=mask(s);simple=p.stem if '$'in p.stem else owner.rsplit('$',1)[-1].rsplit('/',1)[-1]
    matches=list(re.finditer(r'\benum\s+'+re.escape(simple)+r'\b[^{}]*\{',masked))
    if len(matches)!=1:continue
    start=matches[0].end();depth=paren=bracket=0;parts=[];prev=start;end=None
    for i in range(start,len(masked)):
        ch=masked[i]
        if ch=='{':depth+=1
        elif ch=='}':depth-=1
        elif ch=='(':paren+=1
        elif ch==')':paren-=1
        elif ch=='[':bracket+=1
        elif ch==']':bracket-=1
        elif ch in ',;' and depth==paren==bracket==0:
            parts.append(s[prev:i]);prev=i+1
            if ch==';':end=i;break
    if end is None:continue
    blocks={}
    for block in parts:
        name=re.search(r'\b[\w$]+\b',mask(block))
        if name:blocks[name[0]]=block.strip()
    expected=[renames.get(old,old)for _,old,name in constants]
    if set(blocks)!=set(expected):continue
    actual=[re.search(r'\b[\w$]+\b',mask(block))[0]for block in parts]
    if actual!=expected:
        body='\n      '+',\n      '.join(blocks[name]for name in expected)
        s=s[:start]+body+s[end:];p.write_text(s,'utf-8')
    audit.append({'class':owner,'source':p.relative_to(ROOT).as_posix(),'original_constants':[{'ordinal':i,'field':renames.get(old,old),'name':name}for i,old,name in constants],'order_changed':actual!=expected})
prior=json.loads((ROOT/'recovery/enum-constant-restoration.json').read_text('utf-8'))
# Preserve the original repair history when validating again.
byclass={x['class']:x for x in prior}
for x in audit:
    if x['class']not in byclass or x['order_changed']:byclass[x['class']]=x
(ROOT/'recovery/enum-constant-restoration.json').write_text(json.dumps(list(byclass.values()),indent=2),'utf-8')
print('Restored enum constant names',len(renames),'validated enum declaration order',len(audit),'reordered',sum(x['order_changed']for x in audit))
