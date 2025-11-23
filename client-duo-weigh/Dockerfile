# Build stage
FROM gradle:8.5-jdk17-alpine AS build

WORKDIR /app

# Copy gradle files
COPY build.gradle settings.gradle ./
COPY gradle ./gradle

# Copy source code
COPY src ./src

# Build the application
RUN gradle fatJar --no-daemon

# Runtime stage with JavaFX support
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Install required libraries for JavaFX on Alpine
RUN apk add --no-cache \
    ttf-dejavu \
    fontconfig \
    libx11 \
    libxext \
    libxrender \
    libxtst \
    libxi \
    gtk+3.0 \
    mesa-gl

# Copy the built jar from build stage
COPY --from=build /app/build/libs/*-all.jar app.jar

# Create directory for failed weighings
RUN mkdir -p /app/failed_weighings

# Set display for X11 forwarding (if needed)
ENV DISPLAY=:0

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
