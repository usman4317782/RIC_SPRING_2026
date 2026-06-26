# run_network_tests.ps1
# Script to verify network and RMI applications by running servers in background and running clients.

$failed = 0

Write-Host "=======================================================" -ForegroundColor Cyan
Write-Host "  Network & RMI Server-Client Integration Tests        " -ForegroundColor Cyan
Write-Host "=======================================================" -ForegroundColor Cyan

# ------------------------------------------------------------------
# Test 1: TCP Server-Client Chat
# ------------------------------------------------------------------
Write-Host "Testing TCP Server-Client..." -NoNewline
$tcpServerOut = Join-Path (Get-Location) "tcp-server-out.log"
$tcpServerErr = Join-Path (Get-Location) "tcp-server-err.log"
$tcpClientOut = Join-Path (Get-Location) "tcp-client-out.log"
$tcpClientErr = Join-Path (Get-Location) "tcp-client-err.log"

$tcpServerProc = Start-Process java -ArgumentList "TCPServer" -WorkingDirectory "week 14" -PassThru -NoNewWindow -RedirectStandardOutput $tcpServerOut -RedirectStandardError $tcpServerErr
Start-Sleep -Seconds 1.5

if ($tcpServerProc.HasExited) {
    Write-Host " [FAILED] - TCP Server exited early!" -ForegroundColor Red
    $failed++
} else {
    # Run client
    $tcpClientProc = Start-Process java -ArgumentList "TCPClient" -WorkingDirectory "week 14" -PassThru -NoNewWindow -RedirectStandardOutput $tcpClientOut -RedirectStandardError $tcpClientErr -Wait
    
    # Kill server
    Stop-Process -Id $tcpServerProc.Id -Force -ErrorAction SilentlyContinue
    
    # Verify Client output
    if (Test-Path $tcpClientOut) {
        $clientOut = Get-Content $tcpClientOut
        if ($clientOut -match "Received from Server:") {
            Write-Host " [PASSED]" -ForegroundColor Green
        } else {
            Write-Host " [FAILED] - Client output invalid." -ForegroundColor Red
            Write-Host "Client Log content:" -ForegroundColor Gray
            $clientOut | ForEach-Object { Write-Host "  $_" -ForegroundColor Gray }
            $failed++
        }
    } else {
        Write-Host " [FAILED] - Client log not found." -ForegroundColor Red
        $failed++
    }
}

Remove-Item $tcpServerOut -ErrorAction SilentlyContinue
Remove-Item $tcpServerErr -ErrorAction SilentlyContinue
Remove-Item $tcpClientOut -ErrorAction SilentlyContinue
Remove-Item $tcpClientErr -ErrorAction SilentlyContinue

# ------------------------------------------------------------------
# Test 2: UDP Time Server-Client
# ------------------------------------------------------------------
Write-Host "Testing UDP Time Server-Client..." -NoNewline
$udpServerOut = Join-Path (Get-Location) "udp-server-out.log"
$udpServerErr = Join-Path (Get-Location) "udp-server-err.log"
$udpClientOut = Join-Path (Get-Location) "udp-client-out.log"
$udpClientErr = Join-Path (Get-Location) "udp-client-err.log"

$udpServerProc = Start-Process java -ArgumentList "UDPTimeServer" -WorkingDirectory "week 14" -PassThru -NoNewWindow -RedirectStandardOutput $udpServerOut -RedirectStandardError $udpServerErr
Start-Sleep -Seconds 1.5

if ($udpServerProc.HasExited) {
    Write-Host " [FAILED] - UDP Server exited early!" -ForegroundColor Red
    $failed++
} else {
    # Run client
    $udpClientProc = Start-Process java -ArgumentList "UDPTimeClient" -WorkingDirectory "week 14" -PassThru -NoNewWindow -RedirectStandardOutput $udpClientOut -RedirectStandardError $udpClientErr -Wait
    
    # Kill server
    Stop-Process -Id $udpServerProc.Id -Force -ErrorAction SilentlyContinue
    
    # Verify Client output
    if (Test-Path $udpClientOut) {
        $clientOut = Get-Content $udpClientOut
        if ($clientOut -match "Server Response") {
            Write-Host " [PASSED]" -ForegroundColor Green
        } else {
            Write-Host " [FAILED] - Client output invalid." -ForegroundColor Red
            Write-Host "Client Log content:" -ForegroundColor Gray
            $clientOut | ForEach-Object { Write-Host "  $_" -ForegroundColor Gray }
            $failed++
        }
    } else {
        Write-Host " [FAILED] - Client log not found." -ForegroundColor Red
        $failed++
    }
}

Remove-Item $udpServerOut -ErrorAction SilentlyContinue
Remove-Item $udpServerErr -ErrorAction SilentlyContinue
Remove-Item $udpClientOut -ErrorAction SilentlyContinue
Remove-Item $udpClientErr -ErrorAction SilentlyContinue

# ------------------------------------------------------------------
# Test 3: RMI Server-Client
# ------------------------------------------------------------------
Write-Host "Testing RMI Server-Client..." -NoNewline
$rmiServerOut = Join-Path (Get-Location) "rmi-server-out.log"
$rmiServerErr = Join-Path (Get-Location) "rmi-server-err.log"
$rmiClientOut = Join-Path (Get-Location) "rmi-client-out.log"
$rmiClientErr = Join-Path (Get-Location) "rmi-client-err.log"

$rmiServerProc = Start-Process java -ArgumentList "RMIServer" -WorkingDirectory "week 15" -PassThru -NoNewWindow -RedirectStandardOutput $rmiServerOut -RedirectStandardError $rmiServerErr
Start-Sleep -Seconds 2

if ($rmiServerProc.HasExited) {
    Write-Host " [FAILED] - RMI Server exited early!" -ForegroundColor Red
    if (Test-Path $rmiServerErr) {
        Get-Content $rmiServerErr | ForEach-Object { Write-Host "  $_" -ForegroundColor DarkRed }
    }
    $failed++
} else {
    # Run client
    $rmiClientProc = Start-Process java -ArgumentList "RMIClient" -WorkingDirectory "week 15" -PassThru -NoNewWindow -RedirectStandardOutput $rmiClientOut -RedirectStandardError $rmiClientErr -Wait
    
    # Kill server
    Stop-Process -Id $rmiServerProc.Id -Force -ErrorAction SilentlyContinue
    
    # Verify Client output
    if (Test-Path $rmiClientOut) {
        $clientOut = Get-Content $rmiClientOut
        if ($clientOut -match "Remote CalculatorService stub obtained successfully") {
            Write-Host " [PASSED]" -ForegroundColor Green
        } else {
            Write-Host " [FAILED] - Client output invalid." -ForegroundColor Red
            Write-Host "Client Log content:" -ForegroundColor Gray
            $clientOut | ForEach-Object { Write-Host "  $_" -ForegroundColor Gray }
            $failed++
        }
    } else {
        Write-Host " [FAILED] - Client log not found." -ForegroundColor Red
        $failed++
    }
}

Remove-Item $rmiServerOut -ErrorAction SilentlyContinue
Remove-Item $rmiServerErr -ErrorAction SilentlyContinue
Remove-Item $rmiClientOut -ErrorAction SilentlyContinue
Remove-Item $rmiClientErr -ErrorAction SilentlyContinue

Write-Host ""
if ($failed -eq 0) {
    Write-Host "=======================================================" -ForegroundColor Green
    Write-Host "  SUCCESS: All Network tests completed successfully!    " -ForegroundColor Green
    Write-Host "=======================================================" -ForegroundColor Green
} else {
    Write-Host "=======================================================" -ForegroundColor Red
    Write-Host "  FAILURE: $failed Network tests failed.                 " -ForegroundColor Red
    Write-Host "=======================================================" -ForegroundColor Red
    exit 1
}
