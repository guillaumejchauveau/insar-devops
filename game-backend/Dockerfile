FROM openjdk:11-jre

EXPOSE 4444

COPY target/game-backend-*.jar /usr/src/game/game-backend.jar

CMD ["java","-jar","/usr/src/game/game-backend.jar", "http://0.0.0.0:4444/"]
