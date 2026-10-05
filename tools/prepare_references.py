"""Apply verified GDIFF resource patches without executing any client classes."""
import hashlib, pathlib, struct, zipfile
ROOT=pathlib.Path(__file__).resolve().parents[1]
VANILLA=pathlib.Path(r'C:\Users\hp\AppData\Roaming\.minecraft\versions\1.8.9\1.8.9.jar')
OPTIFINE=pathlib.Path(r'C:\Users\hp\AppData\Roaming\.minecraft\libraries\java\preview_OptiFine_1.8.9_HD_U_M6_pre2.jar')
def patch(base,diff):
    assert diff[:5]==bytes.fromhex('d1ffd1ff04')
    p=5;out=bytearray()
    def number(n):
        nonlocal p
        v=int.from_bytes(diff[p:p+n],'big');p+=n;return v
    while True:
        op=number(1)
        if op==0:break
        if op<=248:
            count=op if op<=246 else number(2 if op==247 else 4)
            out.extend(diff[p:p+count]);p+=count
        else:
            widths={249:(2,1),250:(2,2),251:(2,4),252:(4,1),253:(4,2),254:(4,4),255:(8,4)}
            a,b=widths[op];offset=number(a);count=number(b)
            assert offset+count<=len(base)
            out.extend(base[offset:offset+count])
    return bytes(out)
with zipfile.ZipFile(VANILLA) as v,zipfile.ZipFile(OPTIFINE) as of:
    entries={n:v.read(n) for n in v.namelist() if n.endswith('.class')}
    count=0
    for n in of.namelist():
        if n.startswith('patch/') and n.endswith('.class.xdelta'):
            target=n[6:-7]
            if target not in entries:continue
            data=patch(entries[target],of.read(n));md5=of.read(n[:-7]+'.md5').decode().strip()
            assert hashlib.md5(data).hexdigest()==md5,(target,md5)
            entries[target]=data;count+=1
        elif n.endswith('.class'):entries[n]=of.read(n)
    with zipfile.ZipFile(ROOT/'.target/optifine-reference.jar','w',zipfile.ZIP_DEFLATED) as out:
        for n,b in entries.items():out.writestr(n,b)
print('Verified OptiFine patches:',count,'reference classes:',len(entries))
