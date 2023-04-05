#FROM openjdk:11.0-jre-slim
FROM docker.io/library/openjdk:11.0-jre-slim

WORKDIR /work/
RUN chown :root /work \
    && chmod "g+rwX" /work \
    && chown :root /work

COPY target/*-runner.jar /work/application.jar

EXPOSE 8080

CMD ["java","-Xmx512m","-Xms256m","-jar","application.jar","-Dquarkus.http.host=0.0.0.0"]