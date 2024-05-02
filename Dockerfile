FROM eclipse-temurin:17-jre-alpine
WORKDIR /application

RUN addgroup -g 10000 javagroup
RUN adduser -D -s / -u 10000 javauser -G javagroup
RUN chown -R 10000:10000 /application

USER 10000
COPY target/*.war metadata-api.war
ENTRYPOINT ["java", "-war",  "/application/metadata-api.war"]
