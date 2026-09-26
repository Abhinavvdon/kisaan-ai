# Multi-Stage Dockerfile for KISAAN.AI on Render (with integrated Python AI Microservice)

# ------------------------------------------------------------------------------
# Stage 1: Build React + Vite Frontend
# ------------------------------------------------------------------------------
FROM node:20-alpine AS frontend-builder
WORKDIR /app/frontend

COPY frontend/package*.json ./
RUN npm install

COPY frontend/ ./
RUN npm run build

# ------------------------------------------------------------------------------
# Stage 2: Build Spring Boot Backend
# ------------------------------------------------------------------------------
FROM eclipse-temurin:21-jdk-alpine AS backend-builder
WORKDIR /app/backend

# Copy Gradle build files and wrapper
COPY backend/gradle/ ./gradle/
COPY backend/gradlew ./
COPY backend/gradlew.bat ./
COPY backend/build.gradle ./
COPY backend/settings.gradle ./

# Fix Windows CRLF line endings and set execute permissions on gradlew
RUN sed -i 's/\r$//' ./gradlew && chmod +x ./gradlew

# Copy static frontend assets into Spring Boot's static resources folder
COPY --from=frontend-builder /app/frontend/dist/ ./src/main/resources/static/

# Copy backend source code
COPY backend/src/ ./src/

# Set memory limits for Gradle build to fit within Render free tier (512MB RAM)
ENV GRADLE_OPTS="-Dorg.gradle.jvmargs=-Xmx256m -XX:MaxMetaspaceSize=128m"

# Build standalone executable Spring Boot JAR (skipping unit tests during container build)
RUN ./gradlew bootJar --no-daemon -x test

# ------------------------------------------------------------------------------
# Stage 3: Lightweight Production JRE + Python ML Microservice Runtime
# ------------------------------------------------------------------------------
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Install Python 3, venv, curl for health checks
RUN apt-get update && apt-get install -y --no-install-recommends \
    python3 \
    python3-pip \
    python3-venv \
    curl \
    && rm -rf /var/lib/apt/lists/*

# Set up Python virtual environment and install lightweight ML requirements
WORKDIR /app/ml-service
COPY ml-service/requirements.txt ./
RUN python3 -m venv /opt/ml-venv \
    && /opt/ml-venv/bin/pip install --no-cache-dir -r requirements.txt

# Copy ML service application code
COPY ml-service/ ./

WORKDIR /app
RUN mkdir -p /app/data

# Copy compiled JAR from backend builder stage
COPY --from=backend-builder /app/backend/build/libs/*.jar app.jar

# Copy and configure entrypoint script
COPY entrypoint.sh ./
RUN sed -i 's/\r$//' ./entrypoint.sh && chmod +x ./entrypoint.sh

# Render assigns a dynamic port via $PORT (defaults to 8085 locally)
ENV PORT=8085
EXPOSE 8085

ENTRYPOINT ["./entrypoint.sh"]
