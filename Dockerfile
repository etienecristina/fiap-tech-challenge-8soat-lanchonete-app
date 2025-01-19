FROM maven:3.9.8-eclipse-temurin-17-alpine

WORKDIR /app

COPY pom.xml .

COPY src ./src

RUN mvn clean package -Dmaven.test.skip=true

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "target/tech-challenge-1-v1.0.0.jar"]
