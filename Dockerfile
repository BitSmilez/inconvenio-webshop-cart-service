FROM openjdk:19-jdk-alpine
ADD target/product-microservice-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]