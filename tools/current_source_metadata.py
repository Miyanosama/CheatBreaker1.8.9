"""Translate the bytecode evidence to the current reviewed source symbols."""
import json,pathlib,re
ROOT=pathlib.Path(__file__).resolve().parents[1]
def current():
    classes=json.loads((ROOT/'.target/source-repaired.json').read_text('utf-8'))
    cm=json.loads((ROOT/'recovery/confirmed-source-class-aliases.json').read_text('utf-8'))
    mm={x['old']:x['name']for x in json.loads((ROOT/'recovery/reference-method-restoration.json').read_text('utf-8'))}
    mm.update(json.loads((ROOT/'recovery/native-method-restoration.json').read_text('utf-8'))['mapping'])
    main_members=json.loads((ROOT/"recovery/main-configuration-member-restoration.json").read_text("utf-8"))
    mm.update({k:v for k,v in main_members.items()if k.startswith("method_")})
    fm={k:v for k,v in main_members.items()if not k.startswith("method_")}
    extra=ROOT/'recovery/reviewed-extra-member-aliases.json'
    if extra.exists():fm.update(json.loads(extra.read_text('utf-8')))
    for c in classes:
        if c['super']!='java/lang/Enum':continue
        cl=next((m for m in c['methods']if m['name']=='<clinit>'),None)
        if not cl:continue
        ins=cl['ins']
        for i,a in enumerate(ins):
            if len(a)<3 or a[:2]!=[187,'type']or i+3>=len(ins):continue
            if ins[i+1]!=[89]or ins[i+2][0]!='const'or ins[i+3][0]!='const':continue
            name=ins[i+2][1]
            if not isinstance(name,str):continue
            for b in ins[i+4:]:
                if len(b)>4 and b[:2]==[179,'field']and b[2]==c['name']and b[4]=='L'+c['name']+';':
                    if b[3].startswith('recoveredField')and re.fullmatch(r'[A-Za-z_$][\w$]*',name):fm[b[3]]=name
                    break
    def value(s):
        if not isinstance(s,str):return s
        if s in cm:return cm[s]
        if s in mm:return mm[s]
        if s in fm:return fm[s]
        return re.sub(r'L([^;]+);',lambda m:'L'+cm.get(m[1],m[1])+';',s)
    def walk(x):
        if isinstance(x,dict):return {k:walk(v)for k,v in x.items()}
        if isinstance(x,list):return [walk(v)for v in x]
        return value(x)
    inherited_fixes=ROOT/'recovery/inherited-field-reference-restoration.json'
    if inherited_fixes.exists():
        original={c['name']:c for c in classes}
        for fix in json.loads(inherited_fixes.read_text('utf-8')):
            method=next(m for m in original[fix['class']]['methods'] if m['name']==fix['method'] and m['desc']==fix['desc'])
            refs=[i for i in method['ins'] if len(i)>4 and i[1]=='field']
            ref=refs[fix['field_index']]
            assert ref[3]==fix['actual'],fix
            ref[3]=fix['expected']
    translated=walk(classes)
    assert len({c['name'] for c in translated})==len(classes), 'Class identity collision'
    result={c['name']:c for c in translated}
    for fix in json.loads((ROOT/'recovery/enum-readable-name-corrections.json').read_text('utf-8')):
        c=result[fix['class']]
        for f in c['fields']:
            if f['name']==fix['old']:f['name']=fix['name']
        for cc in result.values():
            for method in cc['methods']:
                for ins in method['ins']:
                    if len(ins)>4 and ins[1]=='field'and ins[2]==fix['class']and ins[3]==fix['old']:ins[3]=fix['name']
    return result
if __name__=='__main__':
    p=ROOT/'.target/current-source-symbols.json';p.write_text(json.dumps(list(current().values()),separators=(',',':')),'utf-8');print('Updated current source symbol evidence')
