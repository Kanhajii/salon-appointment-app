# Use official OpenJDK 21 image
FROM eclipse-temurin:21-jdk

# Set working directory inside container
WORKDIR /app

# Copy all project files into container
COPY . .

# Build the jar file (using mvnw wrapper)
RUN ./mvnw clean package -DskipTests

# Run the application
CMD ["java", "-jar", "target/salonAppoinment-0.0.1-SNAPSHOT.jar"]
