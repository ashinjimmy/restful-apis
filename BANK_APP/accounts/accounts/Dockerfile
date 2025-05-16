
# Import base image from openjdk of tag(version) 17
FROM openjdk:17-jdk-slim

#Copy the jar file to a Docker image 
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

#Execute the application
ENTRYPOINT ["java","-jar","accounts-0.0.01-SNAPSHOT.jar"]