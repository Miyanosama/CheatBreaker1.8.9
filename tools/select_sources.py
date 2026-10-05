"""Select complete method bodies across two decompilers, retaining audit sources."""
import json,pathlib,re,shutil
ROOT=pathlib.Path(__file__).resolve().parents[1]
def mask_java(s):
    # Preserve offsets and newlines while suppressing braces inside comments,
    # strings and character literals.
    pattern=re.compile(r'//[^\n]*|/\*.*?\*/|"(?:\\.|[^"\\])*"|\'(?:\\.|[^\'\\])*\'',re.S)
    return pattern.sub(lambda m:''.join('\n' if c=='\n' else ' ' for c in m[0]),s)
def methods(s):
    masked=mask_java(s);pat=re.compile(r'(?m)^[ \t]*(?:(?:public|protected|private|static|final|synchronized|native|abstract)\s+)+(?:[^\n;{}=]*?\s+)?([\w$]+)\s*\(([^{};]*)\)(?:\s+throws\s+[^{}]+)?\s*\{')
    out=[]
    for m in pat.finditer(masked):
        brace=masked.find('{',m.start(),m.end());depth=1;p=brace+1
        while p<len(masked) and depth:
            if masked[p]=='{':depth+=1
            elif masked[p]=='}':depth-=1
            p+=1
        level=masked[:brace].count('{')-masked[:brace].count('}')
        args=m[2].strip();count=0 if not args else args.count(',')+1
        out.append((m.start(),p,m[1],count,level))
    return out
def prepare_view(relative,oldname):
    dest=ROOT/relative
    if dest.exists():
        old=ROOT/'.target'/oldname
        assert dest.resolve().is_relative_to(ROOT) and old.resolve().is_relative_to(ROOT)
        if old.exists():raise RuntimeError('Archive target exists: '+str(old))
        shutil.move(str(dest),str(old))
    dest.mkdir(parents=True,exist_ok=True);return dest
main=prepare_view('src/main/java','old-source-before-final-selection')
archive=prepare_view('recovery/decompiled-complete','old-complete-before-final-selection')
decisions={};failures=[]
for p in (ROOT/'.target/final-complete').rglob('*.java'):
    rel=p.relative_to(ROOT/'.target/final-complete');data=p.read_text('utf-8')
    if "$VF: Couldn't" in data:
        cp=ROOT/'.target/final-complete-cfr'/rel
        if cp.exists():data=cp.read_text('utf-8')
    out=archive/rel;out.parent.mkdir(parents=True,exist_ok=True);out.write_text(data,'utf-8')
for p in (ROOT/'.target/final-cfr').rglob('*.java'):
    rel=p.relative_to(ROOT/'.target/final-cfr');data=p.read_text('utf-8');chosen='CFR 0.152'
    if 'This method has failed to decompile' in data:
        vp=ROOT/'.target/final-vf'/rel
        if vp.exists():
            vf=vp.read_text('utf-8');vm=methods(vf);replacements=[]
            for a,b,name,count,level in methods(data):
                if 'This method has failed to decompile' not in data[a:b]:continue
                candidates=[m for m in vm if m[2:]==(name,count,level)]
                if len(candidates)!=1:candidates=[m for m in vm if m[2]==name and m[3]==count]
                if len(candidates)==1:
                    v=candidates[0];body=vf[v[0]:v[1]]
                    if '<unrepresentable>' not in body and "$VF: Couldn't" not in body:replacements.append((a,b,body))
            for a,b,body in reversed(replacements):data=data[:a]+body+data[b:]
            if replacements:chosen+=' + Vineflower recovered method bodies'
    if 'This method has failed to decompile' in data:failures.append(str(rel))
    out=main/rel;out.parent.mkdir(parents=True,exist_ok=True);out.write_text(data,'utf-8');decisions[str(rel).replace('\\','/')]=chosen
(ROOT/'recovery/source-selection.json').write_text(json.dumps(decisions,indent=2),'utf-8')
(ROOT/'recovery/source-selection-problems.json').write_text(json.dumps(failures,indent=2),'utf-8')
print('Selected main sources:',len(decisions),'complete archive:',len(list(archive.rglob('*.java'))),'unresolved method bodies:',failures)
