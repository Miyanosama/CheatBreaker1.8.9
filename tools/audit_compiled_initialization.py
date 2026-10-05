"""Compare enum identities and static assignment order in compiled source."""
import collections,json,pathlib
ROOT=pathlib.Path(__file__).resolve().parents[1]
N={c['name']:c for c in json.loads((ROOT/'.target/current-source-symbols.json').read_text('utf-8'))}
C={c['name']:c for c in json.loads((ROOT/'.target/compiled-check.json').read_text('utf-8'))}
def assignments(c):
    m=next((m for m in c['methods']if m['name']=='<clinit>'),None)
    return [i[3]for i in m['ins']if len(i)>4 and i[:2]==[179,'field']and i[2]==c['name']]if m else []
def enums(c):
    m=next((m for m in c['methods']if m['name']=='<clinit>'),None)
    if not m:return []
    ins=m['ins'];out=[]
    for i,a in enumerate(ins[:-3]):
        if len(a)<3 or a[:2]!=[187,'type']or ins[i+1]!=[89]or ins[i+2][0]!='const'or ins[i+3][0]!='const':continue
        subtype=C.get(a[2],N.get(a[2],{}))
        if a[2]!=c['name'] and subtype.get('super')!=c['name']:continue
        name,ordinal=ins[i+2][1],ins[i+3][1]
        if not isinstance(name,str)or not isinstance(ordinal,int):continue
        for b in ins[i+4:]:
            if len(b)>4 and b[:2]==[179,'field']and b[2]==c['name']and b[4]=='L'+c['name']+';':out.append((ordinal,name));break
    return sorted(out)
report={'compiled_classes':len(C),'enum_checked':0,'enum_mismatches':[],'static_order_checked':0,'static_order_mismatches':[],'equivalent_switch_table_orders':[],'constant_mismatches':[],'moved_constant_initializers':[],'missing_binary_names':sorted(set(N)-set(C)),'added_binary_names':sorted(set(C)-set(N))}
def const(i):
    if i[0]=='const':return i[1]
    if isinstance(i[0],int) and 2<=i[0]<=8:return i[0]-3
    if i[0]in(9,10):return i[0]-9
    if i[0]in(11,12,13):return float(i[0]-11)
    if i[0]in(14,15):return float(i[0]-14)
    return None
def switch_entries(c):
    m=next((m for m in c['methods']if m['name']=='<clinit>'),None)
    if not m:return {}
    ins=m['ins'];entries={}
    for p,i in enumerate(ins):
        if i!=[79] or p<4:continue
        table,enum,ordinal,value=ins[p-4:p]
        if (len(table)>4 and table[:2]==[178,'field'] and table[2]==c['name']
                and table[4]=='[I' and len(enum)>4 and enum[:2]==[178,'field']
                and len(ordinal)>4 and ordinal[1]=='method' and ordinal[3]=='ordinal'):
            entries[(table[3],enum[2],enum[3])]=const(value)
        else:return {}
    return entries
def switch_domains(owner,classes,entries):
    domains={}
    for name,c in classes.items():
        for m in c['methods']:
            ins=m['ins']
            for index,i in enumerate(ins):
                if i[0]not in (170,171):continue
                refs=[a for a in ins[max(0,index-16):index]if len(a)>4 and a[:2]==[178,'field']and a[2]==owner and a[4]=='[I']
                if not refs:continue
                field=refs[-1][3]
                codes=set(range(i[2],i[3]+1))if i[0]==170 else set(i[2])
                enums=sorted((enum,value)for(table,enum,value),code in entries.items()if table==field and code in codes)
                domains.setdefault((name,m['name'],m['desc'],field),[]).append(enums)
    return domains
for n,c in N.items():
    if n not in C:continue
    cc=C[n]
    if c['super']=='java/lang/Enum':
        expected=enums(c);actual=enums(cc);report['enum_checked']+=1
        if expected!=actual:report['enum_mismatches'].append({'class':n,'original':expected,'compiled':actual})
    original=assignments(c);compiled=assignments(cc)
    existing={f['name']for f in cc['fields']}
    # ConstantValue fields are prepared by the JVM before <clinit>. A source
    # final constant may therefore legitimately omit a PUTSTATIC instruction.
    constants={f['name']for f in cc['fields']if 'value'in f}
    common=set(original)&set(compiled)&existing-constants
    expected=[x for x in original if x in common];actual=[x for x in compiled if x in common]
    if expected:
        report['static_order_checked']+=1
        if expected!=actual:
            row={'class':n,'original':expected,'compiled':actual}
            before=switch_entries(c);after=switch_entries(cc)
            pure_helper=all(f['desc']=='[I'for f in cc['fields']) and all(m['name']=='<clinit>'for m in cc['methods'])
            exact_tables=before and before==after
            reindexed_tables=before and set(before)==set(after) and switch_domains(n,N,before)==switch_domains(n,C,after)
            if pure_helper and (exact_tables or reindexed_tables):
                row['verified_entries']=len(before)
                row['case_codes_reindexed']=not exact_tables
                if not exact_tables:
                    row['verified_consumer_domains']=len(switch_domains(n,N,before))
                    row['behavior_test']='testGzipDecoderStateTransitions'
                report['equivalent_switch_table_orders'].append(row)
            else:report['static_order_mismatches'].append(row)
    cf={f['name']:f for f in cc['fields']}
    for f in c['fields']:
        if 'value'not in f or f['name']not in cf:continue
        out=cf[f['name']]
        if 'value'in out and f['value']!=out['value']:report['constant_mismatches'].append({'class':n,'field':f['name'],'original':f['value'],'compiled':out['value']})
        if 'value'not in out:
            values=[]
            for m in cc['methods']:
                if m['name']not in ('<init>','<clinit>'):continue
                for index,i in enumerate(m['ins']):
                    if len(i)>4 and i[0]in(179,181) and i[1]=='field'and i[2]==n and i[3]==f['name']and index:
                        values.append(const(m['ins'][index-1]))
            default=0 if f['desc']in 'ZBCSIJFD'else None
            row={'class':n,'field':f['name'],'expected':f['value'],'initializer_constants':values,'matches':f['value']in values or (not values and f['value']==default)}
            report['moved_constant_initializers'].append(row)
            if not row['matches']:report['constant_mismatches'].append(row)
(ROOT/'.target/compiled-initialization-audit.json').write_text(json.dumps(report,indent=2),'utf-8')
for key in ['compiled_classes','enum_checked','enum_mismatches','static_order_checked','static_order_mismatches','equivalent_switch_table_orders','constant_mismatches','missing_binary_names','added_binary_names']:
    value=report[key];print(key,len(value)if isinstance(value,list)else value)
for x in report['static_order_mismatches'][:10]:print(x)
if report['enum_mismatches']or report['static_order_mismatches']or report['constant_mismatches']:
    raise SystemExit('Compiled initialization audit requires review')
