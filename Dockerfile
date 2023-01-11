FROM openjdk:8-jdk-alpine3.9 
ARG APP=target/layers
COPY ${APP}/dependencies/ ./
COPY ${APP}/snapshot-dependencies/ ./
COPY ${APP}/spring-boot-loader/ ./
COPY ${APP}/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]


