FROM maven:3.3.2-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
ENV PORT=8080
EXPOSE $PORT
COPY --from=build /app/target/*.jar app.jar
CMD ["java", "-jar", "app.jar"]



