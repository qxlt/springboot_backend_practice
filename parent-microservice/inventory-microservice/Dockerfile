FROM gradle:8.10-jdk-21-and-23-alpine AS builder

COPY wait-for-it.sh /usr/bin/wait-for-it.sh
RUN chmod +x /usr/bin/wait-for-it.sh

COPY --chown=gradle:gradle . /home/gradle/src

WORKDIR /home/gradle/src

RUN gradle build -x test

FROM openjdk:23-jdk

RUN mkdir /app

COPY --from=builder /home/gradle/src/build/libs/*.jar /app/inventory-microservice.jar

ENV POSTGRES_USER=admin\
    POSTGRES_PASSWORD=password

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "/app/inventory-microservice.jar"]