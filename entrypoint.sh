#!/bin/sh
set -e

echo "=========================================================="
echo " Starting KISAAN AI Plant Pathology Microservice (port 8088)"
echo "=========================================================="
/opt/ml-venv/bin/uvicorn app:app --host 127.0.0.1 --port 8088 --workers 1 --app-dir /app/ml-service &

echo "Waiting for Python ML Microservice to initialize..."
for i in $(seq 1 30); do
  if curl -s http://127.0.0.1:8088/health > /dev/null 2>&1; then
    echo "Python Plant Pathology Microservice is active and healthy!"
    break
  fi
  sleep 1
done

echo "=========================================================="
echo " Starting Spring Boot Gateway on port ${PORT:-8085}"
echo "=========================================================="
exec java -Xmx300m -Xss512k -Djava.security.egd=file:/dev/./urandom -Dserver.port=${PORT:-8085} -jar app.jar
