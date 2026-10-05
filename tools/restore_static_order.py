"""Restore extracted static initializers to their original bytecode order."""
import json,pathlib,re
ROOT=pathlib.Path(__file__).resolve().parents[1]
N={c['name']:c for c in json.loads((ROOT/'.target/current-source-symbols.json').read_text('utf-8'))}
aliases=json.loads((ROOT/'recovery/confirmed-source-class-aliases.json').read_text('utf-8'))
for old,new in aliases.items():
    if old in N:N[new]=dict(N[old],name=new)
def mask(s):
    return re.sub(r'//[^\n]*|/\*.*?\*/|"(?:\\.|[^"\\])*"|\'(?:\\.|[^\'\\])*\'',lambda m:''.join('\n' if x=='\n' else ' ' for x in m[0]),s,flags=re.S)
def members(s,m,start,end):
    prev=start;pos=start;out=[]
    while pos<end:
        ch=m[pos]
        if ch==';':out.append((prev,pos+1));prev=pos+1
        elif ch=='{':
            prefix=m[prev:pos];depth=1;j=pos+1
            while j<end and depth:
                if m[j]=='{':depth+=1
                elif m[j]=='}':depth-=1
                j+=1
            # Anonymous and array initializer bodies remain part of a field.
            if '=' not in prefix and not re.match(r'\s*[\w$]+\s*\(',prefix):
                out.append((prev,j));prev=j
            pos=j-1
        pos+=1
    return out
audit=[]
for p in (ROOT/'src/main/java').rglob('*.java'):
    s=p.read_text('utf-8');m=mask(s);root=p.relative_to(ROOT/'src/main/java').with_suffix('').as_posix()
    regions=[]
    for hit in re.finditer(r'\b(class|enum|interface)\s+([\w$]+)[^;{}]*\{',m):
        a=hit.end();depth=1;b=a
        while b<len(m) and depth:
            if m[b]=='{':depth+=1
            elif m[b]=='}':depth-=1
            b+=1
        regions.append((a,b-1,hit[2]))
    # Nested edits first; recompute offsets for every enclosing class.
    for original_a,original_b,simple in sorted(regions,reverse=True):
        m=mask(s)
        hit=next((h for h in re.finditer(r'\b(?:class|enum|interface)\s+'+re.escape(simple)+r'\b[^;{}]*\{',m) if h.end()==original_a),None)
        if not hit:continue
        a=hit.end();depth=1;b=a
        while b<len(m) and depth:
            if m[b]=='{':depth+=1
            elif m[b]=='}':depth-=1
            b+=1
        possible=[n for n in N if n==root and n.rsplit('/',1)[-1]==simple or n.startswith(root+'$')and n.rsplit('$',1)[-1]==simple]
        if len(possible)!=1:continue
        owner=possible[0];c=N[owner];cl=next((x for x in c['methods']if x['name']=='<clinit>'),None)
        if not cl:continue
        order={}
        for i,x in enumerate(cl['ins']):
            if len(x)>4 and x[:2]==[179,'field'] and x[2]==owner:order.setdefault(x[3],i)
        positions={}
        for i,x in enumerate(cl['ins']):
            if len(x)>4 and x[:2]==[179,'field']and x[2]==owner:positions.setdefault(x[3],[]).append(i)
        field_initializers=set()
        units=members(s,m,a,b-1)
        for x,y in units:
            txt=m[x:y];header=txt.split('{',1)[0]
            for name in order:
                if re.search(r'\b'+re.escape(name)+r'\s*=(?!=)',header)and not re.match(r'\s*static\s*$',header):field_initializers.add(name)
        selected=[]
        for x,y in members(s,m,a,b-1):
            txt=m[x:y];header=txt.split('{',1)[0]
            field=re.match(r'\s*(?:(?:public|private|protected|static|final|volatile|transient)\s+)*[\w.$<>?,\[\] ]+?\s+([\w$]+)\s*=',header)
            if field and (c['access']&512 or re.search(r'\bstatic\b',header)):
                name=field[1];selected.append((x,y,order.get(name,-1),name))
            elif re.match(r'\s*static\s*\{',txt):
                assigned=[name for name in order if re.search(r'(?<![\w$])'+re.escape(name)+r'\s*=(?!=)',txt)]
                if assigned:selected.append((x,y,min(positions[v][1]if v in field_initializers and len(positions[v])>1 else positions[v][0]for v in assigned),'<static:'+','.join(assigned)+'>'))
                else:audit.append({'class':owner,'unplaced_static_block':True})
        expected=sorted(selected,key=lambda x:x[2])
        if [x[3]for x in selected]==[x[3]for x in expected]:continue
        texts=[s[x:y]for x,y,_,_ in expected]
        before=[x[3]for x in selected];after=[x[3]for x in expected]
        for (x,y,_,_),txt in reversed(list(zip(selected,texts))):s=s[:x]+txt+s[y:]
        audit.append({'class':owner,'before':before,'after':after})
    if s!=p.read_text('utf-8'):p.write_text(s,'utf-8')
prior=json.loads((ROOT/'recovery/static-initialization-restoration.json').read_text('utf-8'))
(ROOT/'recovery/static-initialization-restoration.json').write_text(json.dumps(prior+audit,indent=2),'utf-8')
print('Restored static initializer order',sum('after'in x for x in audit),'unplaced blocks',sum('unplaced_static_block'in x for x in audit))
