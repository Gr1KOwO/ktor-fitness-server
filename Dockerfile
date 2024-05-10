FROM gradle:7-jdk11
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle buildFatJar --no-daemon

FROM gradle:7-jdk11
EXPOSE 8080
WORKDIR /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/ktor-fitness-server.jar
ENTRYPOINT ["java", "-jar", "/app/ktor-fitness-server.jar"]
