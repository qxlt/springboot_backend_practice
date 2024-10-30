FROM gradle:8.10-jdk22-alpine AS builder

COPY --chown=gradle:gradle . /home/gradle/src

WORKDIR /home/gradle/src

RUN gradle build -x test

FROM openjdk:22-jdk

RUN mkdir /app3

COPY --from=builder /home/gradle/src/build/libs/*.jar /app/order-microservice.jar

ENV POSTGRES_USER=admin\
    POSTGRES_PASSWORD=password

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "/app/order-microservice.jar"]