@echo off
setlocal
title Logistics Management App

REM Default JAVA_HOME if not set
if not defined JAVA_HOME set "JAVA_HOME=C:\Program Files\Java\jdk-17"

REM Java detection: JAVA_HOME then PATH fallback
if exist "%JAVA_HOME%\bin\java.exe" (
    set "JAVA=%JAVA_HOME%\bin\java.exe"
) else (
    where java >nul 2>&1
    if errorlevel 1 (
        echo ERROR: Java not found. Set JAVA_HOME or add java to PATH.
        pause
        exit /b 1
    )
    set "JAVA=java"
)

REM Verify Java version
for /f "tokens=3" %%v in ('"%JAVA%" -version 2^>^&1 ^| findstr /i "version"') do set "JAVA_VER=%%~v"

echo.
echo  =============================================
echo   Logistics Management App
echo  =============================================
echo   Java: %JAVA%
echo   Version: %JAVA_VER%
echo   Port: 8080
echo   URL:  http://localhost:8080
echo  =============================================
echo.

REM Launch browser after 5-second delay
start "" cmd /c "timeout /t 5 /nobreak >nul && start http://localhost:8080"

REM Start the service
"%JAVA%" -jar "%~dp0logistics-mock-service.jar"
