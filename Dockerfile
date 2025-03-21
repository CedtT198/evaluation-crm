# Étape 1 : Builder l'application avec Maven
FROM maven:3.9.4-eclipse-temurin-17 AS builder
WORKDIR /build

# Copier les fichiers de configuration Maven et télécharger les dépendances
COPY pom.xml .
RUN mvn dependency:go-offline

# Copier le code source et compiler l'application
COPY src/ ./src/
# RUN mvn clean package -DskipTests
# RUN mvn clean install -DskipTests

# # Étape 2 : Construire l'image finale
# FROM eclipse-temurin:17-jdk
# WORKDIR /app

# # Copier le JAR généré depuis l'étape de build
# # Remplace "mon-projet.jar" par le nom réel de ton JAR généré si besoin.
# COPY --from=builder /build/target/*.jar app.jar

# EXPOSE 8080
# CMD ["java", "-jar", "app.jar"]

# Commande par défaut : compiler et exécuter l'application
CMD ["mvn", "spring-boot:run"]