FROM eclipse-temurin:21
RUN mkdir /opt/app
COPY target/email-service-*.jar /opt/app/email-service.jar
CMD ["java", "-jar", "/opt/app/email-service.jar"]
