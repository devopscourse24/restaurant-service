# Utilise une image Docker légère d'OpenJDK 17 comme image de base
FROM openjdk:17-jdk-slim-buster

# Définit le répertoire de travail dans le conteneur à /opt
WORKDIR /opt

# Copie le fichier JAR compilé depuis le répertoire target local vers /opt/app.jar dans le conteneur
COPY target/*.jar /opt/restaurantListing-0.0.1.jar

# Définit la commande par défaut pour exécuter l'application, en utilisant la variable d'environnement JAVA_OPTS pour les options JVM
ENTRYPOINT exec java $JAVA_OPTS -jar restaurantListing-0.0.1.jar
