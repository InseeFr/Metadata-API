# Utiliser une image de base qui inclut Tomcat 10 et Java 17.
FROM tomcat:10-jdk17-temurin

# Définir le répertoire de travail pour Tomcat.
WORKDIR /usr/local/tomcat

# Ajouter votre fichier .war dans le dossier webapps de Tomcat.
COPY target/*.war /usr/local/tomcat/webapps/metadata-api.war

# Exposer le port sur lequel Tomcat écoute par défaut.
EXPOSE 8080

# Configurer l'utilisateur pour des raisons de sécurité (si nécessaire).
RUN addgroup --system javagroup && \
    adduser --system --shell /bin/false --uid 10000 --ingroup javagroup --no-create-home javauser && \
    chown -R javauser:javagroup /usr/local/tomcat && \
    chmod -R 755 /usr/local/tomcat/webapps

USER javauser

# Lancer Tomcat.
CMD ["catalina.sh", "run"]
