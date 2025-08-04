# build
FROM maven:3.9.10-eclipse-temurin-21-noble as build
WORKDIR /build

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# runtime
FROM amazoncorretto:21
WORKDIR /app

COPY --from=build /build/target/Service-Discovery-0.0.1-SNAPSHOT.jar ./servicediscovery.jar

EXPOSE 8761

ENV TZ="America/Sao_Paulo"

ENTRYPOINT ["java", "-jar", "servicediscovery.jar"]