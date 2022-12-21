FROM eclipse-temurin:17-jre-jammy
EXPOSE 8080
COPY target/certificate-0.0.1-SNAPSHOT.jar certificate-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/certificate-0.0.1-SNAPSHOT.jar" ]