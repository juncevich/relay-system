FROM openjdk:16.0.1-jdk
MAINTAINER a.juncevich@gmail.com
VOLUME /tmp
COPY build/libs/relay-service-0.0.2-SNAPSHOT.jar data-service.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/relay-service.jar"]
