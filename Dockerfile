# Stage 1: Build the application
FROM maven:3.9.6-eclipse-temurin-17-alpine AS build

# Set the working directory
WORKDIR /build

# Copy the Maven project files
COPY pom.xml /build/
COPY src /build/src/

# Copy the .git directory if it exists
COPY .git /build/.git/

# Build the application using the specified Maven profile
RUN mvn package -DskipTests

# Stage 2: Create the runtime image
FROM eclipse-temurin:17-jre-alpine

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /build/target/*.jar /app/app.jar

# Expose the application port
EXPOSE 8080

# Set the entry point to run the application
CMD ["java", "-jar", "/app/app.jar"]
