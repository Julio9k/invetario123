FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copia POM y wrapper
COPY pom.xml mvnw ./
COPY .mvn .mvn

RUN chmod +x mvnw

# Descarga dependencias para cache (opcional)
RUN ./mvnw dependency:go-offline --batch-mode

# Copia el resto del código
COPY src src

RUN ./mvnw clean package -DskipTests --batch-mode

# Imagen final
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]