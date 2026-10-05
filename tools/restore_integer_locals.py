"""Restore mutable integer locals when original bytecode has no narrowing conversion."""
import json
import pathlib
import re

ROOT = pathlib.Path(__file__).resolve().parents[1]
N = {c['name']: c for c in json.loads((ROOT / '.target/current-source-symbols.json').read_text('utf-8'))}

def mask(s):
    return re.sub(r'//[^\n]*|/\*.*?\*/|"(?:\\.|[^"\\])*"|\'(?:\\.|[^\'\\])*\'',
                  lambda m: ''.join('\n' if c == '\n' else ' ' for c in m[0]), s, flags=re.S)

def end_block(m, a):
    depth = 1
    b = a
    while b < len(m) and depth:
        depth += (m[b] == '{') - (m[b] == '}')
        b += 1
    return b - 1

def arity(desc):
    return len(re.findall(r'\[*(?:L[^;]+;|[BCDFIJSZ])', desc[1:desc.index(')')]))

audit = []
for p in (ROOT / 'src/main/java').rglob('*.java'):
    s = p.read_text('utf-8'); m = mask(s)
    root = p.relative_to(ROOT / 'src/main/java').with_suffix('').as_posix()
    regions = []
    for h in re.finditer(r'\b(?:class|enum|interface)\s+([\w$]+)[^;{}]*\{', m):
        regions.append((h.end(), end_block(m, h.end()), h[1]))
    edits = {}
    headers = r'(?:\b([\w$]+)\s*\(([^{};]*)\)[^{};]*|\b(static)\s*)\{'
    for h in re.finditer(headers, m):
        a = h.end(); b = end_block(m, a)
        containing = [r for r in regions if r[0] <= a <= r[1]]
        if not containing: continue
        region = min(containing, key=lambda r: r[1] - r[0]); simple = region[2]
        owners = [n for n in N if n == root and n.rsplit('/', 1)[-1] == simple
                  or n.startswith(root + '$') and n.rsplit('$', 1)[-1] == simple]
        if len(owners) != 1: continue
        owner = owners[0]
        name = '<clinit>' if h[3] else '<init>' if h[1] == simple else h[1]
        count = 0 if h[3] or not h[2].strip() else len(h[2].split(','))
        methods = [x for x in N[owner]['methods'] if x['name'] == name and arity(x['desc']) == count]
        if not methods: continue
        body = m[a:b]
        for d in re.finditer(r'\b(byte|short)\s+(\w+)\s*(?:=|;)', body):
            op = 145 if d[1] == 'byte' else 147
            if any(any(x[0] == op for x in method['ins']) for method in methods): continue
            var = re.escape(d[2])
            if not re.search(r'\b' + var + r'\s*(?:\+\+|--|[+*/%&|^<>-]+=)|(?:\+\+|--)\s*\b' + var + r'\b', body): continue
            pos = a + d.start(1)
            edits[pos] = (d[1], owner, name, d[2])
    for pos, (old, owner, name, var) in sorted(edits.items(), reverse=True):
        assert s[pos:pos + len(old)] == old
        s = s[:pos] + 'int' + s[pos + len(old):]
        audit.append({'class': owner, 'method': name, 'local': var, 'from': old, 'to': 'int',
                      'evidence': 'mutable local; original method contains no corresponding I2B/I2S'})
    if edits: p.write_text(s, 'utf-8')
(ROOT / 'recovery/integer-local-restoration.json').write_text(json.dumps(audit, indent=2), 'utf-8')
print('Restored integer locals:', len(audit))
