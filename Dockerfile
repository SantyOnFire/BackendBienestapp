# Etapa 1: Build con Maven Wrapper y JDK 21
FROM eclipse-temurin:21 as build

WORKDIR /app

# Copiar TODO el proyecto
COPY . .

# Dar permisos al wrapper
RUN chmod +x mvnw

# Construir la aplicación
RUN ./mvnw -DskipTests package

# Etapa 2: Imagen de ejecución con JRE 21
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
