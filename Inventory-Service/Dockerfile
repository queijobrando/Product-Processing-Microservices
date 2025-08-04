# build
FROM maven:3.9.10-eclipse-temurin-21-noble as build
WORKDIR /build

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# runtime
FROM amazoncorretto:21
WORKDIR /app

COPY --from=build /build/target/Inventory-Service-0.0.1-SNAPSHOT.jar ./inventory.jar

EXPOSE 8082

ENV TZ="America/Sao_Paulo"

ENTRYPOINT ["java", "-jar", "inventory.jar"]