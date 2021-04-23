FROM openjdk:16-jdk-alpine
MAINTAINER a.juncevich@gmail.com
VOLUME /tmp
COPY build/libs/app.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
