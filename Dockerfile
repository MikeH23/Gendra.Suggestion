FROM openjdk:11-jdk-slim as builder
WORKDIR application
EXPOSE 8080:8080
VOLUME /tmp
ARG JAR_FILE=target/original-Gendra-Suggestion-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} suggestion.jar
RUN java -Djarmode=layertools -jar suggestion.jar extract

FROM openjdk:11-jdk-slim
WORKDIR application

COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
RUN ln -sf /usr/share/zoneinfo/America/Mexico_City /etc/localtime
RUN echo "America/Mexico_City" > /etc/timezone
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]