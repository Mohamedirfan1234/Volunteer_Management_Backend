FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/volunteer_backend-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
