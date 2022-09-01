# Start with a base image containing Java runtime
FROM eclipse-temurin:8-jdk-alpine

# Add Maintainer Info
LABEL maintainer="alexander@ververica.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 5656 available to the world outside this container
EXPOSE 5656

ADD target/demo-fraud-webapp-*.jar demo-backend.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=dev","-jar","/demo-backend.jar"]
