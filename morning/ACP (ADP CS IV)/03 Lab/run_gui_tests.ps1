# run_gui_tests.ps1
# Script to verify that GUI programs launch successfully without throwing exceptions.

$guiPrograms = @(
    @{ Dir = "week 7"; Class = "SimpleWindow"; CP = "." },
    @{ Dir = "week 7"; Class = "LayoutManagersDemo"; CP = "." },
    @{ Dir = "week 7"; Class = "TemperatureConverter"; CP = "." },
    @{ Dir = "week 7"; Class = "RegistrationForm"; CP = "." },
    @{ Dir = "week 8"; Class = "CalculatorGUI"; CP = "." },
    @{ Dir = "week 8"; Class = "ColorChangerGUI"; CP = "." },
    @{ Dir = "week 8"; Class = "SimpleDrawingApp"; CP = "." },
    @{ Dir = "week 8"; Class = "MultipleListenersGUI"; CP = "." },
    @{ Dir = "week 9"; Class = "StudentRegistrationForm"; CP = "." },
    @{ Dir = "week 9"; Class = "MenuSystemDemo"; CP = "." },
    @{ Dir = "week 9"; Class = "TextEditor"; CP = "." },
    @{ Dir = "week 9"; Class = "TableDisplayGUI"; CP = "." },
    @{ Dir = "week 11"; Class = "DatabaseGUIDemo"; CP = ".;../lib/sqlite-jdbc.jar" },
    @{ Dir = "week 11"; Class = "LoginSystem"; CP = ".;../lib/sqlite-jdbc.jar" },
    @{ Dir = "week 16"; Class = "CourseManagementSystem"; CP = ".;../lib/sqlite-jdbc.jar" }
)

$failed = 0

Write-Host "=======================================================" -ForegroundColor Cyan
Write-Host "  GUI Launch Validation (2-second smoke tests)        " -ForegroundColor Cyan
Write-Host "=======================================================" -ForegroundColor Cyan

foreach ($prog in $guiPrograms) {
    $dir = $prog.Dir
    $class = $prog.Class
    $cp = $prog.CP
    
    Write-Host "Testing $class in $dir..." -NoNewline
    
    $errFile = Join-Path (Get-Location) "$class-err.log"
    $outFile = Join-Path (Get-Location) "$class-out.log"
    
    # Run java command
    $process = Start-Process java -ArgumentList "-cp", $cp, $class -WorkingDirectory $dir -PassThru -NoNewWindow -RedirectStandardError $errFile -RedirectStandardOutput $outFile
    
    # Wait for 2 seconds
    Start-Sleep -Seconds 2
    
    if ($process.HasExited) {
        Write-Host " [FAILED] - Exited early with code $($process.ExitCode)" -ForegroundColor Red
        if (Test-Path $errFile) {
            $errContent = Get-Content $errFile
            if ($errContent) {
                Write-Host "Error Output:" -ForegroundColor DarkRed
                $errContent | ForEach-Object { Write-Host "  $_" -ForegroundColor DarkRed }
            }
        }
        $failed++
    } else {
        Write-Host " [PASSED]" -ForegroundColor Green
        # Kill the running GUI process
        Stop-Process -Id $process.Id -Force -ErrorAction SilentlyContinue
    }
    
    # Clean up logs
    Remove-Item $errFile -ErrorAction SilentlyContinue
    Remove-Item $outFile -ErrorAction SilentlyContinue
}

Write-Host ""
if ($failed -eq 0) {
    Write-Host "=======================================================" -ForegroundColor Green
    Write-Host "  SUCCESS: All GUI programs launched successfully!      " -ForegroundColor Green
    Write-Host "=======================================================" -ForegroundColor Green
} else {
    Write-Host "=======================================================" -ForegroundColor Red
    Write-Host "  FAILURE: $failed GUI programs failed to launch.       " -ForegroundColor Red
    Write-Host "=======================================================" -ForegroundColor Red
    exit 1
}
