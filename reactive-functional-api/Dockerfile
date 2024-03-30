FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/reactive-api-0.1.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]