FROM openjdk:22
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} user_management_service.jar
ENTRYPOINT ["java", "-jar", "/user_management_service.jar"]