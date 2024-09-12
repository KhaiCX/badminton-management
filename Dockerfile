RUN mvn clean package -DskipTestsgit add Dockerfile

FROM openjdk:17-jdk-alpine
COPY target/badminton-management-0.0.1-SNAPSHOT.jar badminton-management-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "badminton-management-0.0.1-SNAPSHOT.jar"]
