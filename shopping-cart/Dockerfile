FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY . /app
RUN mvn package -Dmaven.test.skip
FROM openjdk:17-jdk-slim
COPY --from=build /app/target/*.jar /app/shopcart-app.jar
ENTRYPOINT ["java", "-jar", "/app/shopcart-app.jar"]
