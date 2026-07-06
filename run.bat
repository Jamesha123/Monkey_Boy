@echo off
setlocal
set "JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-24.0.2.12-hotspot"
set "PATH=%JAVA_HOME%\bin;%PATH%"

if not exist bin\main\Main.class (
    call "%~dp0build.bat"
    if errorlevel 1 exit /b 1
)

java -cp "bin;res" main.Main
