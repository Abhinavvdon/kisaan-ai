#!/bin/sh
set -e

echo "=========================================================="
echo " Starting KISAAN AI Plant Pathology Microservice (port 8088)"
echo "=========================================================="
/opt/ml-venv/bin/uvicorn app:app --host 127.0.0.1 --port 8088 --workers 1 --app-dir /app/ml-service &

echo "=========================================================="
echo " Starting Spring Boot Gateway on port ${PORT:-8085}"
echo "=========================================================="
exec java -Xmx220m -Xss512k -XX:+UseSerialGC -Djava.security.egd=file:/dev/./urandom -Dserver.port=${PORT:-8085} -jar app.jar
