"""Restore casts/boxing lost by decompiler generic inference, using javac diagnostics."""
import json,pathlib,re,collections
ROOT=pathlib.Path(__file__).resolve().parents[1]
errors=json.loads((ROOT/'.target/retained-errors.json').read_text('utf-8'))
groups=collections.defaultdict(list)
for f,l,c,e in errors:groups[pathlib.Path(f.removeprefix('/'))].append((int(l),int(c),e))
audit=[]
for p,errs in groups.items():
    lines=p.read_text('utf-8').splitlines()
    for ln,col,error in sorted(errs,reverse=True):
        s=lines[ln-1];old=s
        if re.search(r'incompatible types: (int|boolean|long|float|double|short|byte|char) cannot be converted to T$',error):
            s=s.replace('(T)', '(T)(Object)',1)
        elif 'incompatible types: java.lang.Object cannot be converted to' in error:
            target=error.split('converted to ',1)[1]
            each=re.search(r'for\s*\(([^:]+?)\s+(\w+)\s*:\s*(.*?)\)\s*\{',s)
            if each:
                typ=each[1].strip();expr=each[3];s=s[:each.start(3)]+'(Iterable<'+typ+'>)(Iterable<?>)('+expr+')'+s[each.end(3):]
        if 'java.lang.Object[] cannot be converted to' in error:
            typ=error.split('converted to ',1)[1]
            # Preserve the exact reference-array cast present in the class file.
            s=s.replace('(Object[])','('+typ+')')
            if s==old:
                match=re.search(r'([\w.]+\.toArray\([^;]*?\))',s)
                if match:s=s[:match.start()]+'('+typ+')'+s[match.start():]
        if s!=old:
            lines[ln-1]=s;audit.append({'file':p.relative_to(ROOT).as_posix(),'line':ln,'before':old,'after':s,'diagnostic':error})
    p.write_text('\n'.join(lines)+'\n','utf-8')
(ROOT/'recovery/generic-source-repairs.json').write_text(json.dumps(audit,indent=2),'utf-8')
print('Restored',len(audit),'generic boxing/cast expressions')
