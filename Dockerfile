FROM maven:3.8.4-openjdk-8 AS builder
WORKDIR /app
COPY . .

#RUN #rm -rf /root/.m2/repository/com/fasterxml/jackson/core/jackson-databind
RUN mvn clean package

FROM openjdk:8
WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
