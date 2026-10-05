"""Package the source-built client without modifying installed game versions."""
import hashlib
import json
import pathlib
import shutil

ROOT = pathlib.Path(__file__).resolve().parents[1]
DEST = ROOT / '.target/client/CheatBreaker1.8.9'
JAR = ROOT / '.target/maven/CheatBreaker1.8.9.jar'
assert JAR.is_file(), 'Run the Maven package phase first'
DEST.mkdir(parents=True, exist_ok=True)
shutil.copy2(JAR, DEST / JAR.name)
shutil.copytree(ROOT / 'natives', DEST / 'CheatBreaker1.8.9-natives', dirs_exist_ok=True)
libraries = json.loads((ROOT / 'recovery/launcher-dependencies.json').read_text('utf-8'))
for lib in libraries:
    source = ROOT / lib['path']
    assert hashlib.sha256(source.read_bytes()).hexdigest() == lib['sha256'], source
    (DEST / 'libraries').mkdir(exist_ok=True)
    shutil.copy2(source, DEST / 'libraries' / source.name)
manifest = json.loads((ROOT / 'build/original-version.json').read_text('utf-8'))
manifest['id'] = 'CheatBreaker1.8.9'
manifest['clientVersion'] = '1.8.9'
manifest['type'] = 'custom'
manifest.pop('downloads', None)
(DEST / 'CheatBreaker1.8.9.json').write_text(json.dumps(manifest, indent=2), 'utf-8')
for source in (ROOT / '.target/tool-classes').glob('OfflineSmokeLaunch*.class'):
    (DEST / 'offline-tools').mkdir(exist_ok=True)
    shutil.copy2(source, DEST / 'offline-tools' / source.name)
launch = r'''param(
    [string]$JavaHome = $env:RECOVERY_JAVA_HOME,
    [string]$Username = 'RecoveryPlayer',
    [string]$Uuid = '00000000000000000000000000000001',
    [string]$AccessToken = '0',
    [string]$AssetsDir = "$env:APPDATA\.minecraft\assets",
    [switch]$Offline
)
$ErrorActionPreference = 'Stop'
if (-not $JavaHome -and (Test-Path -LiteralPath 'F:\Program Files\Java\zulu-8\bin\java.exe')) {
    $JavaHome = 'F:\Program Files\Java\zulu-8'
}
if (-not $JavaHome) { $JavaHome = $env:JAVA_HOME }
$java = if ($JavaHome) { Join-Path $JavaHome 'bin\java.exe' } else { 'java.exe' }
$version = & $java -version 2>&1 | Out-String
if ($version -notmatch 'version "1\.8\.') { throw 'Use Java 8; set RECOVERY_JAVA_HOME.' }
$nativeDir = Join-Path $PSScriptRoot 'CheatBreaker1.8.9-natives'
$jar = Join-Path $PSScriptRoot 'CheatBreaker1.8.9.jar'
$libs = Join-Path $PSScriptRoot 'libraries\*'
$gameDir = Join-Path $PSScriptRoot 'game'
New-Item -ItemType Directory -Path $gameDir -Force | Out-Null
$cp = "$jar;$libs"
$main = 'net.minecraft.client.main.Main'
if ($Offline) {
    $cp = (Join-Path $PSScriptRoot 'offline-tools') + ";$cp"
    $main = 'OfflineSmokeLaunch'
}
$arguments = @('-Xmx2G', '-Dfile.encoding=UTF-8', '-Duser.language=en',
    "-Djava.library.path=$nativeDir", "-Dorg.lwjgl.librarypath=$nativeDir", '-cp', $cp, $main,
    '--username', $Username, '--version', 'CheatBreaker1.8.9', '--gameDir', $gameDir,
    '--assetsDir', $AssetsDir, '--assetIndex', '1.8', '--uuid', $Uuid,
    '--accessToken', $AccessToken, '--userProperties', '{}', '--userType', 'mojang')
Set-Location -LiteralPath $PSScriptRoot
& $java @arguments
exit $LASTEXITCODE
'''
(DEST / 'launch.ps1').write_text(launch, 'utf-8')
for name, flag in [('launch.bat', ''), ('launch-offline.bat', ' -Offline')]:
    (DEST / name).write_text('@echo off\r\npowershell -NoProfile -ExecutionPolicy Bypass -File "%~dp0launch.ps1"'
                             + flag + ' %*\r\n', 'utf-8')
files = sorted(p for p in DEST.rglob('*') if p.is_file() and 'game' not in p.relative_to(DEST).parts)
inventory = {p.relative_to(DEST).as_posix(): hashlib.sha256(p.read_bytes()).hexdigest() for p in files}
(ROOT / '.target/client/distribution-sha256.json').write_text(json.dumps(inventory, indent=2), 'utf-8')
print('Packaged source-built client:', DEST)
print('Source JAR SHA-256:', inventory[JAR.name])
