#-----------
#BUILD STAGE
#-----------


FROM gradle:8-jdk-21-and-23-alpine AS builder

COPY --chown=gradle:gradle . /home/gradle/src


WORKDIR /home/gradle/src

RUN gradle build -x test

#-----------
#PACKAGE STAGE
#-----------
FROM openjdk:23-jdk

RUN mkdir /app

COPY --from=builder /home/gradle/src/build/libs/*.jar /app/notification-microservices.jar


EXPOSE 8089

ENTRYPOINT ["java", "-jar", "/app/notification-microservices.jar"]