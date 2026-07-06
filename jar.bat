@echo off
setlocal
set "JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-24.0.2.12-hotspot"
set "PATH=%JAVA_HOME%\bin;%PATH%"

call "%~dp0build.bat"
if errorlevel 1 exit /b 1

if exist "%~dp0MonkeyBoy.jar" (
    jar uf "%~dp0MonkeyBoy.jar" -C "%~dp0bin" .
    if errorlevel 1 exit /b 1
    echo JAR updated: MonkeyBoy.jar
    exit /b 0
)

if not exist res\Map (
    echo Missing game assets in res\. Place assets in res\ or keep an existing MonkeyBoy.jar to update.
    exit /b 1
)

jar cfm "%~dp0MonkeyBoy.jar" "%~dp0MANIFEST.MF" -C "%~dp0bin" . -C "%~dp0res" .
if errorlevel 1 exit /b 1

echo JAR build complete: MonkeyBoy.jar
