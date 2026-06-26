# build_all.ps1
# PowerShell script to compile all Java files for the ACP Lab week-by-week.

$buildFailed = $false
$weeks = 1..16

Write-Host "=======================================================" -ForegroundColor Cyan
Write-Host "  Advanced Computer Programming (ACP) Lab Build Script " -ForegroundColor Cyan
Write-Host "=======================================================" -ForegroundColor Cyan
Write-Host ""

foreach ($w in $weeks) {
    $dir = "week $w"
    if (Test-Path $dir) {
        Write-Host "--- Building Week $w ($dir) ---" -ForegroundColor Yellow
        $files = Get-ChildItem -Path $dir -Filter "*.java" | ForEach-Object { $_.FullName }
        if ($files) {
            # Check if it requires SQLite
            if ($w -eq 10 -or $w -eq 11 -or $w -eq 16) {
                javac -cp ".;lib/*" $files
            } else {
                javac $files
            }
            
            if ($LASTEXITCODE -ne 0) {
                Write-Host "Error compiling $dir" -ForegroundColor Red
                $buildFailed = $true
            } else {
                Write-Host "Successfully compiled $dir" -ForegroundColor Green
            }
        } else {
            Write-Host "No Java files found in $dir" -ForegroundColor Gray
        }
        Write-Host ""
    }
}

if (-not $buildFailed) {
    Write-Host "=======================================================" -ForegroundColor Green
    Write-Host "  BUILD SUCCESSFUL! All files compiled successfully.  " -ForegroundColor Green
    Write-Host "=======================================================" -ForegroundColor Green
} else {
    Write-Host "=======================================================" -ForegroundColor Red
    Write-Host "  BUILD FAILED! Some errors were encountered.          " -ForegroundColor Red
    Write-Host "=======================================================" -ForegroundColor Red
    exit 1
}
