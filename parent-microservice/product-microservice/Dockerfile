FROM gradle:8-jdk-21-and-23-alpine AS builder

COPY --chown=gradle:gradle . /home/gradle/src

WORKDIR /home/gradle/src

RUN gradle build -x test

FROM openjdk:23-jdk

RUN mkdir /app

COPY --from=builder /home/gradle/src/build/libs/*.jar /app/product-microservice.jar

ENV MONGO_DB_USERNAME=admin\
    MONGO_DB_PWD=password

EXPOSE 8086

ENTRYPOINT ["java", "-jar", "/app/product-microservice.jar"]