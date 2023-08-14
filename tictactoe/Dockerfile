# Use a base image with Java
FROM openjdk:17

# Set a working directory
WORKDIR /app

# Copy your JAR file (replace with the path to your JAR)
COPY build/libs/tictactoe-0.0.1-SNAPSHOT.jar /app/tictactoe.jar

# Command to start your app
CMD ["java", "-jar", "/app/tictactoe.jar"]

# Expose the port your app runs on
EXPOSE 8080
