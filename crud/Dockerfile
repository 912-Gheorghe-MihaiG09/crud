FROM openjdk:17-jdk-sdo
ARG JAR_FILE=target/*.jar
COPY ./target/crud-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]