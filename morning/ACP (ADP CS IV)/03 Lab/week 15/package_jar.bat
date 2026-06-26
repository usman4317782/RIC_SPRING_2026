@echo off
echo ===================================================
echo   Compiling and Packaging Java Application to JAR  
echo ===================================================

:: 1. Compile Java files
echo Compiling java files...
javac RMIInterface.java RMIImpl.java RMIServer.java RMIClient.java
if %errorlevel% neq 0 (
    echo Compilation failed!
    pause
    exit /b %errorlevel%
)

:: 2. Create Manifest details
echo Creating manifest file...
echo Main-Class: RMIClient> Manifest.txt

:: 3. Create Jar Archive
echo Packaging class files into RMICalculator.jar...
jar cfm RMICalculator.jar Manifest.txt RMIInterface.class RMIImpl.class RMIServer.class RMIClient.class
if %errorlevel% neq 0 (
    echo Packaging failed!
    del Manifest.txt
    pause
    exit /b %errorlevel%
)

:: 4. Clean up temporary files
echo Cleaning up build classes and manifests...
del Manifest.txt
del RMIInterface.class
del RMIImpl.class
del RMIServer.class
del RMIClient.class

echo Success! RMICalculator.jar packaged successfully.
echo You can run the packaged JAR client using: java -jar RMICalculator.jar
pause
