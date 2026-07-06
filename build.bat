@echo off
setlocal
set "JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-24.0.2.12-hotspot"
set "PATH=%JAVA_HOME%\bin;%PATH%"

if not exist bin mkdir bin

for /r src %%f in (*.java) do set "SOURCES=!SOURCES! "%%f""
setlocal enabledelayedexpansion
set "SOURCES="
for /r src %%f in (*.java) do set "SOURCES=!SOURCES! "%%f""

javac -encoding UTF-8 -d bin -sourcepath src !SOURCES!
if errorlevel 1 exit /b 1

echo Build complete.
