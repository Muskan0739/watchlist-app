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

# Create a persistent volume for H2 database files
VOLUME /data

# Copy the JAR built in the previous stage
COPY --from=build /app/target/*.jar Watchlist.jar

# Expose port 8082
EXPOSE 8082

ENTRYPOINT ["java","-jar","Watchlist.jar"]
