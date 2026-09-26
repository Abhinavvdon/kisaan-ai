@echo off
echo ===================================================================
echo             KISAAN.AI - Full Stack Launcher
echo ===================================================================
echo [1/3] Starting Python Plant Pathology AI Microservice (port 8088)...
start "KISAAN - AI Service" cmd /k "cd /d %~dp0ml-service && .venv\Scripts\uvicorn.exe app:app --host 127.0.0.1 --port 8088"

echo [2/3] Starting Spring Boot Backend API (port 8085)...
start "KISAAN - Backend API" cmd /k "cd /d %~dp0backend && gradlew.bat bootRun"

echo [3/3] Starting Vite React Frontend (port 5173)...
start "KISAAN - Frontend" cmd /k "cd /d %~dp0frontend && npm run dev"

echo.
echo All services launched! Opening web browser in 5 seconds...
timeout /t 5 >nul
start http://localhost:5173
echo ===================================================================
