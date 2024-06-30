# Stage 1: Build the application
FROM maven:3.8.5-openjdk-17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and other necessary files for Maven to download dependencies
COPY pom.xml .

# Download dependencies
RUN mvn dependency:go-offline

# Copy the rest of the application source code
COPY src ./src

# Package the application
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM openjdk:17-jdk-slim

# Expose the application port
EXPOSE 8080

# Copy the jar file from the build stage to the run stage
COPY --from=build /app/target/demo-1.jar app.jar

# Define the entry point for the container
ENTRYPOINT ["java", "-jar", "app.jar"]
