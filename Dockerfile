FROM maven:3.6.2 as mvn
WORKDIR /metadata-api
COPY ./ /metadata-api/
RUN mvn -B -f /metadata-api/pom.xml package


FROM tomcat:8-jdk8
COPY --from=mvn metadata-api/target/metadata-api.war /usr/local/tomcat/webapps/
ADD ./config/start.sh /usr/local/tomcat
RUN chmod +x /usr/local/tomcat/start.sh
ENTRYPOINT [ "/usr/local/tomcat/start.sh"]
CMD ["catalina.sh", "run"]