# Imagen base para construir el JAR
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo pom.xml y el wrapper primero (para aprovechar cache)
COPY pom.xml mvnw ./
COPY .mvn .mvn

# Da permisos de ejecución al wrapper
RUN chmod +x mvnw

# Descarga dependencias (para cache)
RUN ./mvnw dependency:go-offline

# Copia el resto del código
COPY src src

# Compila el proyecto y genera el JAR
RUN ./mvnw clean package -DskipTests

# ---- Imagen final (más ligera) ----
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copia el JAR desde la etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto (Render usa PORT env var)
EXPOSE 8080

# Comando para ejecutar la app
ENTRYPOINT ["java", "-jar", "app.jar"]