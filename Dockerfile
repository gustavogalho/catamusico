#
# Build stage
#
FROM maven:3.5.4-jdk-8-slim AS build
COPY src /catamusico/src
COPY pom.xml /catamusico
RUN mvn -f /catamusico/pom.xml clean package

#
# Package stage
#
FROM openjdk:8-jre-slim
COPY --from=build /catamusico/target/catamusico-1.0.0.jar /usr/local/lib/demo.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/demo.jar"]
