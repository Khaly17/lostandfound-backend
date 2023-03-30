FROM openjdk:19-jdk-alpine
ENV APP_HOME=/app
WORKDIR $APP_HOME

COPY target/lostandfound.jar $APP_HOME

EXPOSE 8080
CMD ["java", "-jar", "lostandfound.jar"]
