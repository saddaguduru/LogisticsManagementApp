@echo off
setlocal
title Building Logistics Management App

if not defined JAVA_HOME set "JAVA_HOME=C:\Program Files\Java\jdk-17"

echo.
echo Building Logistics Management App from source...
echo.

cd /d "%~dp0mock-service-src"
call mvnw.cmd clean package -DskipTests

if errorlevel 1 (
    echo.
    echo BUILD FAILED
    pause
    exit /b 1
)

copy /Y "target\mock-service-0.0.1-SNAPSHOT.jar" "%~dp0logistics-mock-service.jar"

echo.
echo =============================================
echo  Build successful!
echo  JAR copied to: %~dp0logistics-mock-service.jar
echo  Run start.bat to launch the application.
echo =============================================
echo.
pause
