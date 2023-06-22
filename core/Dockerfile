# Use a base image with Java and Gradle installed
FROM openjdk:17-jdk-slim AS builder

# Set the working directory
WORKDIR /app

# Copy the Gradle wrapper files
COPY gradlew .
COPY gradle gradle

# Copy the build.gradle and settings.gradle files
COPY build.gradle .
COPY settings.gradle .

# Copy the source code
COPY src src

# Build the project
RUN ./gradlew build --no-daemon --continue

# Create a new Docker image with just the JAR file
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file from the builder stage
COPY --from=builder /app/build/libs/core-0.0.1-SNAPSHOT.jar .

# Set the entry point to run the JAR file
ENTRYPOINT ["java", "-jar", "core-0.0.1-SNAPSHOT.jar"]
