"""Audit every recovered class, instruction and bundled resource; no game runs."""
import hashlib, json, pathlib, re, sys, unittest, zipfile
ROOT=pathlib.Path(__file__).resolve().parents[1]
sys.stdout.reconfigure(encoding='utf-8')
P={c['name']:c for c in json.loads((ROOT/'.target/preview.json').read_text('utf-8'))}
N={c['name']:c for c in json.loads((ROOT/'.target/named.json').read_text('utf-8'))}
CM={};FM={};MM={}
for l in (ROOT/'recovery/final-names.tsv').read_text('utf-8').splitlines():
    a=l.split('\t')
    if a[0]=='CLASS':CM[a[1]]=a[2]
    elif a[0]=='FIELD':FM[tuple(a[1:4])]=a[4]
    elif a[0]=='METHOD':MM[tuple(a[1:4])]=a[4]
def desc(d):return re.sub(r'L([^;]+);',lambda m:'L'+CM.get(m[1],m[1])+';',d)
def member(o,n,d,mp,seen=None):
    if seen is None:seen=set()
    if o in seen:return None
    seen.add(o)
    if (o,n,d) in mp:return mp[o,n,d]
    c=P.get(o)
    if c:
        for p in [c.get('super'),*c['interfaces']]:
            v=member(p,n,d,mp,seen)
            if v:return v
    return None
def ins(i):
    a=list(i)
    if len(a)>2 and a[1] in ('field','method'):
        a[3]=member(a[2],a[3],a[4],FM if a[1]=='field' else MM) or a[3];a[2]=CM.get(a[2],desc(a[2]));a[4]=desc(a[4])
    elif len(a)>2 and a[1]=='type':a[2]=CM.get(a[2],desc(a[2]))
    elif len(a)>2 and a[1]=='array':a[2]=desc(a[2])
    elif a[0]=='const':
        if isinstance(a[1],dict) and 'type' in a[1]:a[1]={'type':desc(a[1]['type'])}
        elif isinstance(a[1],str):
            s=a[1]
            if s in CM:a[1]=CM[s]
            elif s.replace('.','/') in CM:a[1]=CM[s.replace('.','/')].replace('/','.')
    return a
class RecoveryAudit(unittest.TestCase):
    def test_class_inventory_and_unique_names(self):
        self.assertEqual(len(P),5139);self.assertEqual(set(N),{CM.get(n,n) for n in P});self.assertEqual(len(N),len(P))
        self.assertFalse(any(re.fullmatch('[Il]{20,}',n) for n in N))
    def test_all_decompiled_classes_are_present(self):
        archive=ROOT/'recovery/decompiled-complete'
        missing=[n for n in N if not (archive/(n+'.java')).exists()]
        self.assertEqual(missing,[],'Missing complete decompilations')
        failed=[str(p.relative_to(archive)) for p in archive.rglob('*.java') if "$VF: Couldn't" in p.read_text('utf-8') or 'This method has failed to decompile' in p.read_text('utf-8')]
        self.assertEqual(failed,[],'Failed method bodies in complete archive')
    def test_fields_preserved_in_authoritative_named_jar(self):
        for o,c in P.items():
            actual={(f['name'],f['desc']) for f in N[CM.get(o,o)]['fields']}
            expected={(FM.get((o,f['name'],f['desc']),f['name']),desc(f['desc'])) for f in c['fields']}
            self.assertEqual(expected,actual,o)
    def test_all_method_bodies_preserved_after_symbol_remapping(self):
        for o,c in P.items():
            actual={(m['name'],m['desc']):m for m in N[CM.get(o,o)]['methods']}
            self.assertEqual(len(actual),len(c['methods']),o)
            for m in c['methods']:
                key=(MM.get((o,m['name'],m['desc']),m['name']),desc(m['desc']))
                self.assertIn(key,actual,o)
                expected=[ins(i) for i in m['ins']];got=actual[key]['ins']
                # invokedynamic bootstrap handles contain symbol descriptors in
                # their text representation; ASM remaps these independently.
                for a,b in zip(expected,got):
                    if len(a)>2 and a[1]=='dynamic':a[2:]=b[2:]
                self.assertEqual(expected,got,o+'/'+m['name'])
    def test_resource_bytes_match_original(self):
        with zipfile.ZipFile(ROOT/'.target/input/preview.jar') as z:
            for n in z.namelist():
                if n.endswith('/') or n.endswith('.class') or re.fullmatch(r'META-INF/.*\.(SF|RSA|DSA)',n):continue
                self.assertEqual(hashlib.sha256(z.read(n)).digest(),hashlib.sha256((ROOT/'src/main/resources'/n).read_bytes()).digest(),n)
    def test_selected_source_has_no_decompiler_failure_placeholders(self):
        bad=[]
        for p in (ROOT/'src/main/java').rglob('*.java'):
            s=p.read_text('utf-8')
            if '<unrepresentable>' in s or "$VF: Couldn't" in s or 'This method has failed to decompile' in s:bad.append(str(p.relative_to(ROOT)))
        self.assertEqual(bad,[])
if __name__=='__main__':
    result=unittest.TextTestRunner(verbosity=2).run(unittest.defaultTestLoader.loadTestsFromTestCase(RecoveryAudit))
    report={'checks':result.testsRun,'failures':len(result.failures),'errors':len(result.errors),'passed':result.wasSuccessful(),'meaning':'Recovery audit only; does not assert successful source compilation or runtime equivalence of cleaned source'}
    (ROOT/'.target/audit.json').write_text(json.dumps(report,indent=2),'utf-8')
    sys.exit(0 if result.wasSuccessful() else 1)
