@echo off
SETLOCAL ENABLEDELAYEDEXPANSION

:: ================================
::  Color Setup
:: ================================
for /f "tokens=1,2 delims==" %%a in ('"prompt $H & for %%b in (1) do rem"') do set "BS=%%a"

set "GREEN=%BS%[32m"
set "RED=%BS%[31m"
set "YELLOW=%BS%[33m"
set "BLUE=%BS%[34m"
set "RESET=%BS%[0m"

cls

echo %BLUE%===========================================
echo        PROJECT-X: FULL WORKFLOW START
echo ===========================================%RESET%

:: ================================
::  Set Environment Variables
:: ================================
echo %YELLOW%>> Setting DB Variables...%RESET%
set DB_USERNAME=root
set DB_PASSWORD=12345678
set DB_NAME=lmsdb

echo DB_USERNAME=%DB_USERNAME%
echo DB_PASSWORD=********
echo DB_NAME=%DB_NAME%

:: ================================
::  Run Tests
:: ================================
echo.
echo %BLUE%===========================================
echo   Running Maven Tests
echo ===========================================%RESET%

mvn clean test > test.log 2>&1

if %ERRORLEVEL% NEQ 0 (
    echo %RED%Tests FAILED. Check test.log for details.%RESET%
    pause
    exit /b 1
) else (
    echo %GREEN%All tests passed successfully.%RESET%
)

:: ================================
::  Build Package
:: ================================
echo.
echo %BLUE%===========================================
echo   Building Maven Package
echo ===========================================%RESET%

mvn clean package > build.log 2>&1

if %ERRORLEVEL% NEQ 0 (
    echo %RED%Build FAILED. Check build.log for details.%RESET%
    pause
    exit /b 1
) else (
    echo %GREEN%Build completed successfully.%RESET%
)

:: ================================
::  Run Spring Boot Application
:: ================================
echo.
echo %BLUE%===========================================
echo   Starting Application
echo ===========================================%RESET%

mvn spring-boot:run

echo.
echo %BLUE%===========================================
echo        PROJECT-X WORKFLOW COMPLETE
echo ===========================================%RESET%
pause
