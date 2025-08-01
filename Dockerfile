# Imagen base con Java 21
FROM eclipse-temurin:21-jdk as build

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo pom.xml y descarga dependencias
COPY pom.xml .
RUN ./mvnw dependency:go-offline || true

# Copia el código fuente
COPY . .

# Compila el proyecto y genera el .jar
RUN ./mvnw clean package -DskipTests

# ---- Imagen final (más ligera) ----
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copia el .jar generado desde la imagen de build
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto (Render usará $PORT)
EXPOSE 8080

# Comando para ejecutar la app
CMD ["java", "-jar", "app.jar"]
