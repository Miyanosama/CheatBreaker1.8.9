@echo off
setlocal
set "RECOVERY_ROOT=%~dp0"
set "RECOVERY_MVN=%RECOVERY_ROOT%.target\tools\apache-maven-3.9.11\bin\mvn.cmd"
if defined RECOVERY_JAVA_HOME set "JAVA_HOME=%RECOVERY_JAVA_HOME%"
if not defined RECOVERY_JAVA_HOME if exist "F:\Program Files\Java\zulu-8\bin\java.exe" set "JAVA_HOME=F:\Program Files\Java\zulu-8"
if not exist "%RECOVERY_MVN%" (
  powershell -NoProfile -ExecutionPolicy Bypass -File "%RECOVERY_ROOT%tools\bootstrap-maven.ps1"
  if errorlevel 1 exit /b 1
)
call "%RECOVERY_MVN%" -f "%RECOVERY_ROOT%pom.xml" %*
exit /b %errorlevel%
