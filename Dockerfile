FROM openjdk:11-slim

LABEL maintainer="Daniel Dutra <danieldhsd@gmail.com>"

ENTRYPOINT ["java", "-jar", "/app/news-service.jar"]

ARG JAR_FILE

ADD ${JAR_FILE} /app/news-service.jar
