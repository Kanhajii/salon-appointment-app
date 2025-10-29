# Use official OpenJDK 21 image
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy Maven wrapper and project files
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src ./src

# Give execution permission and build the app
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

# Expose the port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "target/salonAppoinment-0.0.1-SNAPSHOT.jar"]
