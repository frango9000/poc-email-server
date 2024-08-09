FROM eclipse-temurin:21
RUN mkdir /opt/app
COPY target/spam-service-*.jar /opt/app/spam-service.jar
CMD ["java", "-jar", "/opt/app/spam-service.jar"]
