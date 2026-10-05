@echo off
setlocal
call "%~dp0mvnw.cmd" test
if errorlevel 1 exit /b 1
call "%~dp0mvnw.cmd" package
if errorlevel 1 exit /b 1
python "%~dp0tools\package_client.py"
exit /b %errorlevel%
