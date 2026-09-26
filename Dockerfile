# Multi-Stage Dockerfile for KISAAN.AI on Render

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

# Build standalone executable Spring Boot JAR (skipping unit tests during container build)
RUN ./gradlew bootJar --no-daemon -x test

# ------------------------------------------------------------------------------
# Stage 3: Lightweight Production JRE Runtime
# ------------------------------------------------------------------------------
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Create directory for persistent local database storage if H2 is used
RUN mkdir -p /app/data

# Copy compiled JAR from backend builder stage
COPY --from=backend-builder /app/backend/build/libs/*.jar app.jar

# Render assigns a dynamic port via $PORT (defaults to 8085 locally)
ENV PORT=8085
EXPOSE 8085

# Fast container startup with secure random seed
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Dserver.port=${PORT}", "-jar", "app.jar"]
