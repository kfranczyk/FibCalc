# syntax=docker/dockerfile:1.2
FROM openjdk:8-jdk-alpine3.9 as build
WORKDIR /workspace/app-source

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw install -DskipTests
RUN mkdir -p target/extracted && (java -Djarmode=layertools -jar target/*.jar extract --destination target/extracted)


FROM openjdk:8-jre-alpine3.9
WORKDIR /app
ARG EXTRACTED=/workspace/app-source/target/extracted

EXPOSE 8090

COPY --from=build ${EXTRACTED}/dependencies/ ./
COPY --from=build ${EXTRACTED}/spring-boot-loader/ ./
COPY --from=build ${EXTRACTED}/snapshot-dependencies/ ./
COPY --from=build ${EXTRACTED}/application/ ./
ENTRYPOINT ["java","org.springframework.boot.loader.JarLauncher"]
