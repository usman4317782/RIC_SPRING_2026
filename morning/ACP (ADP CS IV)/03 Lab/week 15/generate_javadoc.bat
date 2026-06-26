@echo off
echo ===================================================
echo   Generating JavaDoc HTML Documentation  
echo ===================================================

:: Create target doc folder
if not exist doc mkdir doc

echo Invoking javadoc tool...
javadoc -d doc RMIInterface.java RMIImpl.java RMIServer.java RMIClient.java
if %errorlevel% neq 0 (
    echo JavaDoc generation failed!
    pause
    exit /b %errorlevel%
)

echo Success! JavaDoc pages generated in 'doc/' directory.
echo You can view the documentation by opening 'doc/index.html' in your browser.
pause
