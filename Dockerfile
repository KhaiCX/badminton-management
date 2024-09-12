# Sử dụng hình ảnh JDK làm base image
FROM openjdk:17-jdk-alpine as build

# Sao chép file .jar vào container
COPY target/badminton-management-0.0.1-SNAPSHOT.jar badminton-management-0.0.1-SNAPSHOT.jar

# Mở cổng 8080
EXPOSE 8080

# Chạy ứng dụng
ENTRYPOINT ["java", "-jar", "badminton-management-0.0.1-SNAPSHOT.jar"]
