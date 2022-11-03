# syntax = docker/dockerfile:1.4.0

FROM maven:3.8-openjdk-11 AS builder

WORKDIR /usr/src/cpoo-project/

COPY pom.xml ./
RUN --mount=type=cache,target=/root/.m2 mvn -B dependency:resolve-plugins dependency:go-offline

COPY . ./
# -offline does not work, see https://issues.apache.org/jira/browse/MDEP-82
RUN --mount=type=cache,target=/root/.m2 mvn -B -DskipTests -Dmaven.test.skip=true clean package

FROM openjdk:11-jre-slim

EXPOSE 4444
EXPOSE 9464

ADD https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/latest/download/opentelemetry-javaagent.jar /opt/

RUN <<EOF cat > /etc/otel-agent.properties
otel.metrics.exporter=prometheus
otel.traces.exporter=none
EOF

COPY --from=builder /usr/src/cpoo-project/target/game-backend-*.jar /usr/src/cpoo-project/game-backend.jar

CMD ["java", "-javaagent:/opt/opentelemetry-javaagent.jar",\
  "-Dotel.javaagent.configuration-file=/etc/otel-agent.properties",\
  "-jar", "/usr/src/cpoo-project/game-backend.jar"]
