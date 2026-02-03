FROM maven:3.9.6-eclipse-temurin-21 AS build
# Nutzt Maven mit Java 21 für den Build-Prozess
WORKDIR /app
# Setzt das Arbeitsverzeichnis im Container auf /app
COPY pom.xml .
# Kopiert die Abhängigkeitsliste (Maven-Konfiguration)
RUN mvn dependency:go-offline                 # Lädt alle Bibliotheken vorab herunter (Cache-Optimierung)
COPY src ./src
# Kopiert den eigentlichen Java-Quellcode
RUN mvn clean package -DskipTests             # Kompiliert den Code und erstellt die .jar Datei (ohne Tests)

FROM eclipse-temurin:21-jdk-jammy
# Nutzt ein kleineres Java-Image nur für die Ausführung
WORKDIR /app
# Setzt wieder das Arbeitsverzeichnis
COPY --from=build /app/target/*.jar app.jar
# Kopiert NUR die fertige .jar aus der ersten Phase
EXPOSE 8080
# Dokumentiert, dass die App auf Port 8080 läuft
ENTRYPOINT ["java", "-jar", "app.jar"]
# Befehl, der die Anwendung beim Start ausführt