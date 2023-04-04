FROM openjdk:11
ARG JAR_FILE=/build/libs/samsion-0.0.1-SNAPSHOT.jar
COPY  ${JAR_FILE} samsion.jar
ENTRYPOINT ["java","-jar","/samsion.jar","--spring.profiles.active=prod"]