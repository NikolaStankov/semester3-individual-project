FROM azul/zulu-openjdk-alpine:17

VOLUME /tmp

EXPOSE 8080

ARG JAR_FILE=./build/libs/school-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} /app.jar

ENTRYPOINT ["java","-jar","/app.jar"]