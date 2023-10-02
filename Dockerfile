FROM openjdk:8
EXPOSE 8080
RUN mkdir -p /app/
ADD build/libs/proxy-service-0.0.1-SNAPSHOT.jar /app/proxy-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/app/proxy-service-0.0.1-SNAPSHOT.jar"]