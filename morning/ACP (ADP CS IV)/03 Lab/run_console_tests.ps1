# run_console_tests.ps1
# Script to run all console Java programs in sequence and verify they execute successfully using direct pipeline execution.

$consolePrograms = @(
    @{ Dir = "week 1"; Class = "HelloWorld"; CP = "."; Input = $null },
    @{ Dir = "week 1"; Class = "ModifiedHelloWorld"; CP = "."; Input = $null },
    @{ Dir = "week 2"; Class = "PrimitiveDataTypes"; CP = "."; Input = $null },
    @{ Dir = "week 2"; Class = "OperatorsDemo"; CP = "."; Input = $null },
    @{ Dir = "week 2"; Class = "SimpleCalculator"; CP = "."; Input = "12`n*`n3`n" },
    @{ Dir = "week 3"; Class = "MainWeek3"; CP = "."; Input = $null },
    @{ Dir = "week 3"; Class = "MethodOverloadingDemo"; CP = "."; Input = $null },
    @{ Dir = "week 4"; Class = "InheritanceDemo"; CP = "."; Input = $null },
    @{ Dir = "week 4"; Class = "InterfaceDemo"; CP = "."; Input = $null },
    @{ Dir = "week 4"; Class = "PolymorphismDemo"; CP = "."; Input = $null },
    @{ Dir = "week 4"; Class = "AbstractClassDemo"; CP = "."; Input = $null },
    @{ Dir = "week 5"; Class = "TryCatchFinallyDemo"; CP = "."; Input = $null },
    @{ Dir = "week 5"; Class = "CustomExceptionDemo"; CP = "."; Input = $null },
    @{ Dir = "week 5"; Class = "CheckedUncheckedDemo"; CP = "."; Input = $null },
    @{ Dir = "week 5"; Class = "FileReaderExceptionDemo"; CP = "."; Input = $null },
    @{ Dir = "week 6"; Class = "TextFileIO"; CP = "."; Input = $null },
    @{ Dir = "week 6"; Class = "SerializationDemo"; CP = "."; Input = $null },
    @{ Dir = "week 6"; Class = "ContactBook"; CP = "."; Input = "1`nAlice`n12345`nalice@example.com`n2`n3`n" },
    @{ Dir = "week 6"; Class = "StreamComparison"; CP = "."; Input = $null },
    @{ Dir = "week 10"; Class = "SQLExecutionDemo"; CP = ".;../lib/sqlite-jdbc.jar"; Input = $null },
    @{ Dir = "week 10"; Class = "StudentCRUD"; CP = ".;../lib/sqlite-jdbc.jar"; Input = $null },
    @{ Dir = "week 10"; Class = "DatabaseMetadataDemo"; CP = ".;../lib/sqlite-jdbc.jar"; Input = $null },
    @{ Dir = "week 11"; Class = "PreparedStatementDemo"; CP = ".;../lib/sqlite-jdbc.jar"; Input = $null },
    @{ Dir = "week 11"; Class = "TransactionDemo"; CP = ".;../lib/sqlite-jdbc.jar"; Input = $null },
    @{ Dir = "week 12"; Class = "LambdaConversionDemo"; CP = "."; Input = $null },
    @{ Dir = "week 12"; Class = "FunctionalInterfaceDemo"; CP = "."; Input = $null },
    @{ Dir = "week 12"; Class = "BuiltInFunctionalInterfaces"; CP = "."; Input = $null },
    @{ Dir = "week 13"; Class = "ThreadExtensionDemo"; CP = "."; Input = $null },
    @{ Dir = "week 13"; Class = "RunnableInterfaceDemo"; CP = "."; Input = $null },
    @{ Dir = "week 13"; Class = "ThreadLifecycleDemo"; CP = "."; Input = $null },
    @{ Dir = "week 13"; Class = "ThreadPriorityDemo"; CP = "."; Input = $null },
    @{ Dir = "week 14"; Class = "ThreadSynchronizationDemo"; CP = "."; Input = $null },
    @{ Dir = "week 14"; Class = "ProducerConsumerDemo"; CP = "."; Input = $null }
)

$failed = 0

Write-Host "=======================================================" -ForegroundColor Cyan
Write-Host "  Console Application Runner & Verification            " -ForegroundColor Cyan
Write-Host "=======================================================" -ForegroundColor Cyan

foreach ($prog in $consolePrograms) {
    $dir = $prog.Dir
    $class = $prog.Class
    $cp = $prog.CP
    $inputData = $prog.Input
    
    Write-Host "Running $class in $dir..." -NoNewline
    
    $errFile = Join-Path (Get-Location) "$class-err.log"
    $outFile = Join-Path (Get-Location) "$class-out.log"
    
    Push-Location $dir
    
    try {
        if ($inputData -ne $null) {
            # Run with piped input and redirect both stdout and stderr
            $inputData | java -cp $cp $class 2> $errFile > $outFile
        } else {
            # Run without input and redirect both stdout and stderr
            java -cp $cp $class 2> $errFile > $outFile
        }
        
        $exitCode = $LASTEXITCODE
        
        if (Test-Path $errFile) {
            $errContent = Get-Content $errFile
            if ($errContent) {
                Write-Host " [FAILED] - Standard error generated:" -ForegroundColor Red
                $errContent | ForEach-Object { Write-Host "  $_" -ForegroundColor DarkRed }
                $failed++
                continue
            }
        }
        
        if ($exitCode -ne 0) {
            Write-Host " [FAILED] - Exit code was $exitCode" -ForegroundColor Red
            $failed++
        } else {
            Write-Host " [PASSED]" -ForegroundColor Green
        }
    } catch {
        Write-Host " [FAILED] - Exception thrown: $_" -ForegroundColor Red
        $failed++
    } finally {
        Pop-Location
        # Clean up logs
        Remove-Item $errFile -ErrorAction SilentlyContinue
        Remove-Item $outFile -ErrorAction SilentlyContinue
    }
}

Write-Host ""
if ($failed -eq 0) {
    Write-Host "=======================================================" -ForegroundColor Green
    Write-Host "  SUCCESS: All Console programs completed successfully! " -ForegroundColor Green
    Write-Host "=======================================================" -ForegroundColor Green
} else {
    Write-Host "=======================================================" -ForegroundColor Red
    Write-Host "  FAILURE: $failed Console programs failed to run correctly." -ForegroundColor Red
    Write-Host "=======================================================" -ForegroundColor Red
    exit 1
}
