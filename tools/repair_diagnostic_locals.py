"""Restore receiver types/declarations at remaining compiler diagnostic sites."""
import collections,json,pathlib,re
ROOT=pathlib.Path(__file__).resolve().parents[1];src=ROOT/'src/main/java'
slog=(ROOT/'.target/pass7-maven.log').read_text('utf-16')
pat=r'\[ERROR\] (.*?\.java):\[(\d+),(\d+)\] cannot find symbol\s+\[ERROR\]\s+symbol:\s+(.*?)\s+\[ERROR\]\s+location:\s+(.*)'
changes=collections.defaultdict(dict)
for file,line,col,symbol,location in re.findall(pat,slog):
    m=re.match(r'variable (\w+) of type java.lang.Object$',location)
    if not m:continue
    var=m[1];method=re.match(r'method ([\w$]+)\(',symbol)
    if not method:continue
    typ={'put':'java.util.Map','get':'java.util.Map','add':'java.util.Collection','containsValue':'com.google.common.collect.BiMap','inverse':'com.google.common.collect.BiMap','size':'java.util.Map','isEmpty':'java.util.Collection','length':'String','getHostAddress':'java.net.InetAddress','newInstance':'Class<?>','close':'java.io.Closeable','invoke':'java.lang.reflect.Method','format':'java.text.DateFormat','parse':'java.text.DateFormat','setTimeZone':'java.text.DateFormat','getNodeType':'org.w3c.dom.Node','getTagName':'org.w3c.dom.Element','getPath':'java.net.URL'}.get(method[1])
    if not typ:continue
    p=pathlib.Path(file.removeprefix('/'));text=p.read_text('utf-8');offset=sum(len(x)+1 for x in text.splitlines()[:int(line)-1]);decls=list(re.finditer(r'\b(?:final )?Object\s+'+re.escape(var)+r'\b',text[:offset]))
    if not decls:continue
    d=decls[-1]
    context=text[d.start():offset]
    if 'HashMultimap.create' in context and var=='var1':typ='com.google.common.collect.Multimap'
    if typ=='java.util.Map' and 'inverse()' in context:typ='com.google.common.collect.BiMap'
    changes[p][d.start()]=(d.end(),typ+' '+var)
for p,items in changes.items():
    s=p.read_text('utf-8')
    for start,(end,value) in sorted(items.items(),reverse=True):s=s[:start]+value+s[end:]
    p.write_text(s,'utf-8')
p=src/'io/netty/util/internal/chmv8/ConcurrentHashMapV8.java';s=p.read_text('utf-8')
# Some loop-header variables were lost completely, although their JVM slot is known.
for var in ['var5','var6','var7','var8']:
    s=re.sub(r'(?m)^(\s*)while \(\('+var+r' = this\.advance\(\)\)',lambda m:m[1]+'ConcurrentHashMapV8.Node<K,V> '+var+';\n'+m[0],s)
p.write_text(s,'utf-8')
# Restore Boolean CHECKCAST on every direct Setting read used as a condition.
for p in src.rglob('*.java'):
    s=p.read_text('utf-8');old=s
    expr=r'(?:\b\w+\.)*(?:\b\w+\([^()]*\)\.)*(?:\b\w+\.)*\b\w+\.getValue\(\)'
    s=re.sub(r'(?<!\))('+expr+r')(?=\s*(?:\?|&&|\|\|))',r'(Boolean)\1',s)
    s=re.sub(r'((?:&&|\|\|)\s*)('+expr+r')',r'\1(Boolean)\2',s)
    if s!=old:p.write_text(s,'utf-8')
print('Reconstructed local declarations',sum(len(v)for v in changes.values()))
