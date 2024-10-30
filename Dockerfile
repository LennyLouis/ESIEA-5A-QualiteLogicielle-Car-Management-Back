FROM maven:3.8.4-openjdk-8-slim AS build
WORKDIR /app
COPY . .
# Ajout de logs pour le build
RUN echo "Maven build starting..." && \
    mvn clean package -DskipTests && \
    echo "Maven build finished" && \
    ls -la target/

FROM tomcat:8.5-jdk8-openjdk-slim
# On garde le nom du war original
COPY --from=build /app/target/esieaBack.war /usr/local/tomcat/webapps/esieaBack.war
# Ajout de logs pour vérifier le déploiement
RUN ls -la /usr/local/tomcat/webapps/

# Ajout de CATALINA_OPTS pour plus de logs
ENV CATALINA_OPTS="-Dcom.sun.jersey.config.feature.Debug=true"

EXPOSE 8080
CMD ["catalina.sh", "run"]