FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY .. /app
RUN mvn package -Dmaven.test.skip
FROM openjdk:17-jdk-slim
EXPOSE 8084
COPY --from=build /app/target/*.jar /app/payments.jar
ENTRYPOINT ["java", "-jar", "/app/payments.jar"]