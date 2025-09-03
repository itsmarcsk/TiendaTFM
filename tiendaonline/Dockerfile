# Etapa 1: Construir el proyecto con Maven y Java 17
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml ./
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Ejecutar el .jar con una imagen ligera de Java 17
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Configurar variables de entorno (puedes personalizarlas según tu aplicación)
ENV JAVA_OPTS="-Xms512m -Xmx1024m"

# Exponer el puerto de la aplicación
EXPOSE 8080

# Usar ENTRYPOINT para ejecutar la aplicación con opciones configurables
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]