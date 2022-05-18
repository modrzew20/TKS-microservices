FROM openjdk:17
VOLUME /tmp
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/WEB-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/WEB-INF/classes /app
ENTRYPOINT ["java", "-cp","app:app/lib/*", "com.example.applicationdocker.ApplicationDockerApplication"]