#Standard image to customize
FROM openjdk:8-jdk-alpine
#FROM  java:8
# Arg build time definitionARG  JAR_FILE
ARG JAR_FILE=vilear-coursemanager-1.0-SNAPSHOT.jar
#Volume to create a bridge between OS Host and Docker
VOLUME /tmp
#Run exec commands. Each RUN instruction create a top layer on image.
RUN mkdir /app \
    && mkdir /app/logs \
    && mkdir /app/config
#Port to expose container
EXPOSE 8080
#Copy target jar to docker root folder
ADD ${JAR_FILE} /app/vilear-coursemanager-1.0-SNAPSHOT.jar
#Docker root workdir and Entrypoints exec instructions
ENTRYPOINT exec java $JAVA_OPTS -jar /app/vilear-coursemanager-1.0-SNAPSHOT.jar