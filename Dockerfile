# ---------- BUILD STAGE ----------

FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
# Build the Spring Boot app and package it into a JAR
RUN mvn clean package -DskipTests

# ---------- RUN STAGE ----------
FROM eclipse-temurin:21-jre
WORKDIR /app

# Creates a persistent volume for H2 database files
VOLUME /data

COPY --from=build /app/target/*.jar Watchlist.jar

EXPOSE 5000
ENTRYPOINT ["java","-jar","Watchlist.jar"]
