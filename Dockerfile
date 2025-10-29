# Use official Java 21 image
FROM openjdk:21-jdk-slim

# Set working directory
WORKDIR /app

# Copy pom.xml and source code
COPY pom.xml .
COPY src ./src

# Build the application using Maven Wrapper
COPY mvnw .
COPY .mvn .mvn
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

# Run the Spring Boot app
EXPOSE 8080
CMD ["java", "-jar", "target/salonAppoinment-0.0.1-SNAPSHOT.jar"]
