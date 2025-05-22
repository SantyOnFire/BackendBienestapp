# Etapa 1: construir la aplicación
FROM maven:3.9.5-eclipse-temurin-17 AS build
WORKDIR /app

# Copiamos los archivos del proyecto y construimos el JAR
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: ejecutar la aplicación
FROM eclipse-temurin:17
WORKDIR /app

# Copiamos el JAR desde la etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Exponemos el puerto (ajusta si es diferente)
EXPOSE 8080

# Comando para ejecutar tu app
ENTRYPOINT ["java", "-jar", "app.jar"]
