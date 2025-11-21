# Etapa 1: Build con Maven Wrapper y JDK 21
FROM eclipse-temurin:21 as build

WORKDIR /app

# Copiar mvnw y darle permisos
COPY mvnw .
COPY .mvn .mvn
RUN chmod +x mvnw

# Copiar el resto
COPY pom.xml .
COPY src src

# Construir la app
RUN ./mvnw -DskipTests package

# Etapa 2: Imagen liviana para producción con JRE 21
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
