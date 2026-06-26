@echo off
echo =======================================================
echo   Advanced Computer Programming (ACP) Lab Build Script 
echo =======================================================
echo.

set BUILD_FAILED=0

:: Week 1
echo --- Building Week 1 ---
javac "week 1\*.java"
if %errorlevel% neq 0 ( set BUILD_FAILED=1 )

:: Week 2
echo --- Building Week 2 ---
javac "week 2\*.java"
if %errorlevel% neq 0 ( set BUILD_FAILED=1 )

:: Week 3
echo --- Building Week 3 ---
javac "week 3\*.java"
if %errorlevel% neq 0 ( set BUILD_FAILED=1 )

:: Week 4
echo --- Building Week 4 ---
javac "week 4\*.java"
if %errorlevel% neq 0 ( set BUILD_FAILED=1 )

:: Week 5
echo --- Building Week 5 ---
javac "week 5\*.java"
if %errorlevel% neq 0 ( set BUILD_FAILED=1 )

:: Week 6
echo --- Building Week 6 ---
javac "week 6\*.java"
if %errorlevel% neq 0 ( set BUILD_FAILED=1 )

:: Week 7
echo --- Building Week 7 ---
javac "week 7\*.java"
if %errorlevel% neq 0 ( set BUILD_FAILED=1 )

:: Week 8
echo --- Building Week 8 ---
javac "week 8\*.java"
if %errorlevel% neq 0 ( set BUILD_FAILED=1 )

:: Week 9
echo --- Building Week 9 ---
javac "week 9\*.java"
if %errorlevel% neq 0 ( set BUILD_FAILED=1 )

:: Week 10 (Requires SQLite driver)
echo --- Building Week 10 ---
javac -cp ".;lib\*" "week 10\*.java"
if %errorlevel% neq 0 ( set BUILD_FAILED=1 )

:: Week 11 (Requires SQLite driver)
echo --- Building Week 11 ---
javac -cp ".;lib\*" "week 11\*.java"
if %errorlevel% neq 0 ( set BUILD_FAILED=1 )

:: Week 12
echo --- Building Week 12 ---
javac "week 12\*.java"
if %errorlevel% neq 0 ( set BUILD_FAILED=1 )

:: Week 13
echo --- Building Week 13 ---
javac "week 13\*.java"
if %errorlevel% neq 0 ( set BUILD_FAILED=1 )

:: Week 14
echo --- Building Week 14 ---
javac "week 14\*.java"
if %errorlevel% neq 0 ( set BUILD_FAILED=1 )

:: Week 15
echo --- Building Week 15 ---
javac "week 15\*.java"
if %errorlevel% neq 0 ( set BUILD_FAILED=1 )

:: Week 16 (Requires SQLite driver)
echo --- Building Week 16 ---
javac -cp ".;lib\*" "week 16\*.java"
if %errorlevel% neq 0 ( set BUILD_FAILED=1 )

echo.
if %BUILD_FAILED% equ 0 (
    echo =======================================================
    echo   BUILD SUCCESSFUL! All week files compiled successfully.
    echo =======================================================
) else (
    echo =======================================================
    echo   BUILD FAILED! Some errors were encountered. Check logs.
    echo =======================================================
)
pause
