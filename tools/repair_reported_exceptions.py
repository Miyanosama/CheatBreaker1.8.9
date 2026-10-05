"""Add stripped checked exception declarations reported by javac; never wrap errors."""
import collections,json,pathlib,re,sys
ROOT=pathlib.Path(__file__).resolve().parents[1]
log=ROOT/sys.argv[1];raw=log.read_bytes();text=raw.decode('utf-16') if raw[:2] in [b'\xff\xfe',b'\xfe\xff'] else raw.decode('utf-8',errors='replace')
pat=r'\[ERROR\] (.*?\.java):\[(\d+),(\d+)\] unreported exception ([\w.$]+); must be caught or declared to be thrown'
groups=collections.defaultdict(list)
for f,l,c,e in set(re.findall(pat,text)):groups[pathlib.Path(f.removeprefix('/'))].append((int(l),e))
count=0;audit=[]
for p,errors in groups.items():
    s=p.read_text('utf-8');headers=collections.defaultdict(set)
    for line,exception in errors:
        offset=sum(len(x)+1 for x in s.splitlines()[:line-1]);pattern=r'(?m)^[ \t]*(?:(?:public|private|protected|static|final|synchronized)\s+)+[^{;=]+?\([^{};]*\)(?: throws [^{]+)?\s*\{'
        matches=list(re.finditer(pattern,s[:offset]))
        if not matches:print('No method declaration found:',p,line);continue
        m=matches[-1];headers[(m.start(),m.end(),m[0])].add(exception)
    for (start,end,header),exceptions in sorted(headers.items(),reverse=True):
        new=header[:-1].rstrip()
        for e in sorted(exceptions):
            if e.rsplit('.',1)[-1] not in header:
                new+=(', ' if ' throws ' in new else ' throws ')+e;count+=1
                audit.append({'source':p.relative_to(ROOT).as_posix(),'header':header.strip(),'exception':e})
        s=s[:start]+new+' {'+s[end:]
    p.write_text(s,'utf-8')
path=ROOT/'recovery/compiler-exception-repairs.json';previous=json.loads(path.read_text('utf-8')) if path.exists() else [];path.write_text(json.dumps(previous+audit,indent=2),'utf-8')
print('Restored checked exception declarations',count)
sys.exit(0 if count else 2)
