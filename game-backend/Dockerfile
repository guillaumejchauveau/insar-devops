FROM maven:3.8-openjdk-11 AS builder

WORKDIR /usr/src/cpoo-project/

COPY pom.xml ./
RUN --mount=type=cache,target=/root/.m2 mvn -B dependency:resolve-plugins dependency:go-offline

COPY . ./
# -offline does not work, see https://issues.apache.org/jira/browse/MDEP-82
RUN --mount=type=cache,target=/root/.m2 mvn -B -DskipTests -Dmaven.test.skip=true clean package

FROM openjdk:11-jre-slim

EXPOSE 4444

COPY --from=builder /usr/src/cpoo-project/target/game-backend-*.jar /usr/src/cpoo-project/game-backend.jar

CMD ["java", "-jar", "/usr/src/cpoo-project/game-backend.jar"]
