FROM openjdk:24-jdk

COPY target/demo-0.0.2-SNAPSHOT.jar /app/app.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "/app/app.jar"]