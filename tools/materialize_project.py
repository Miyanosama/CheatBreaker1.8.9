import hashlib, json, pathlib, re, shutil, zipfile
ROOT=pathlib.Path(__file__).resolve().parents[1]
VERSION=pathlib.Path(r'C:\Users\hp\AppData\Roaming\.minecraft\versions\CheatBreaker-preview-1.8.9')
LIBS=VERSION.parents[1]/'libraries'
dest=ROOT/'src/main/java';dest.mkdir(parents=True,exist_ok=True)
selection={};stub=re.compile(r"\$VF: Couldn't|This method has failed to decompile")
for p in (ROOT/'.target/vf-final').rglob('*.java'):
    rel=p.relative_to(ROOT/'.target/vf-final');data=p.read_text('utf-8');chosen='Vineflower 1.11.2'
    if stub.search(data):
        other=ROOT/'.target/cfr-final'/rel
        alt=other.read_text('utf-8') if other.exists() else ''
        if alt and not stub.search(alt):data=alt;chosen='CFR 0.152'
    out=dest/rel;out.parent.mkdir(parents=True,exist_ok=True);out.write_text(data,'utf-8');selection[str(rel).replace('\\','/')]=chosen
(ROOT/'recovery/decompiler-selection.json').write_text(json.dumps(selection,indent=2),'utf-8')
resources=ROOT/'src/main/resources';resources.mkdir(parents=True,exist_ok=True)
jar=VERSION/'CheatBreaker-preview-1.8.9.jar'
with zipfile.ZipFile(jar) as z:
    count=0
    for n in z.namelist():
        if n.endswith('/') or n.endswith('.class') or re.fullmatch(r'META-INF/.*\.(SF|RSA|DSA)',n):continue
        out=resources/n
        if not out.resolve().is_relative_to(resources.resolve()):raise ValueError(n)
        out.parent.mkdir(parents=True,exist_ok=True);out.write_bytes(z.read(n));count+=1
build=ROOT/'build';build.mkdir(exist_ok=True)
shutil.copy2(VERSION/'CheatBreaker-preview-1.8.9.json',build/'original-version.json')
shutil.copytree(VERSION/'CheatBreaker-preview-1.8.9-natives',ROOT/'natives',dirs_exist_ok=True)
inputs=ROOT/'.target/input';inputs.mkdir(parents=True,exist_ok=True);shutil.copy2(jar,inputs/'preview.jar')
depdir=ROOT/'.target/launcher-libs';depdir.mkdir(exist_ok=True);dependencies=[]
config=json.loads((build/'original-version.json').read_text('utf-8'))
for library in config['libraries']:
    group,artifact,version=library['name'].split(':')[:3]
    filename=artifact+'-'+version+'.jar';p=LIBS/group.replace('.','/')/artifact/version/filename
    if p.exists():
        out=depdir/filename;shutil.copy2(p,out);dependencies.append({'coordinate':library['name'],'path':str(out.relative_to(ROOT)).replace('\\','/'),'sha256':hashlib.sha256(out.read_bytes()).hexdigest()})
(ROOT/'recovery/launcher-dependencies.json').write_text(json.dumps(dependencies,indent=2),'utf-8')
provenance={'input_jar':str(jar),'sha256':hashlib.sha256(jar.read_bytes()).hexdigest(),'reference_source_tree':r'F:\Work\CheatBreakerZ\src\main\java','reference_binary':r'F:\Work\CheatBreakerZ\.target\maven\original-cheatbreaker-client-1.0-SNAPSHOT.jar','minecraft_version':'1.8.9','optifine_version':'1.8.9_HD_U_M6_pre2','decompiled_source_files':len(selection),'resources_extracted':count,'unrecoverable_metadata':['Original comments','Original local variable names','Original formatting','Unmapped original symbol names'],'source_profile':'full-source','notes':'This is a reconstructed project. Reference implementations supply names and structural evidence only; all method bodies come from the supplied preview JAR.'}
(ROOT/'recovery/provenance.json').write_text(json.dumps(provenance,indent=2,ensure_ascii=False),'utf-8')
print('Materialized',len(selection),'Java sources,',count,'resources,',len(dependencies),'launcher libraries')
