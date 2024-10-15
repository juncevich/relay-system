#FROM openjdk:16.0.1-jdk
#MAINTAINER a.juncevich@gmail.com
#VOLUME /tmp
#COPY build/libs/data-service-0.0.2-SNAPSHOT.jar data-service.jar
#EXPOSE 8080
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/data-service.jar"]

#FROM openjdk:16.0.1-jdk
#
#ADD . .
#
#ENTRYPOINT java -jar example-0.0.1-SNAPSHOT.jar

FROM eclipse-temurin:23-alpine

WORKDIR /app
ADD BOOT-INF/lib /app/lib
ADD META-INF /app/META-INF
ADD BOOT-INF/classes /app

ENTRYPOINT java -cp .:./lib/* com.relay.RelaySystemApplication