$ErrorActionPreference = 'Stop'
$recoveryRoot = Split-Path -Parent $PSScriptRoot
$recoveryTools = Join-Path $recoveryRoot '.target/tools'
New-Item -ItemType Directory -Force -Path $recoveryTools | Out-Null
$recoveryZip = Join-Path $recoveryTools 'apache-maven-3.9.11-bin.zip'
Invoke-WebRequest -UseBasicParsing 'https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/3.9.11/apache-maven-3.9.11-bin.zip' -OutFile $recoveryZip
Expand-Archive -LiteralPath $recoveryZip -DestinationPath $recoveryTools -Force
