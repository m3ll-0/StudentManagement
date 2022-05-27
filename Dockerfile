# Dockerfile
FROM openjdk:17-jdk-alpine
MAINTAINER jordyvanekelen
COPY student-management/student/target/student-1.0.0.jar student-1.0.0.jar
ENTRYPOINT ["java","-jar","/student-1.0.0.jar"]