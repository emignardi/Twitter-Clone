FROM openjdk:22
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} message_service.jar
ENTRYPOINT ["java", "-jar", "/message_service.jar"]