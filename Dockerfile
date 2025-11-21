# Etapa 1: Build con Maven Wrapper
FROM eclipse-temurin:17 as build

WORKDIR /app

# Copiar mvnw y darle permisos de ejecución
COPY mvnw .
COPY .mvn .mvn
RUN chmod +x mvnw

# Copiar el resto del proyecto
COPY pom.xml .
COPY src src

# Empaquetar la app sin tests
RUN ./mvnw -DskipTests package

# Etapa 2: Imagen liviana para producción
FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
