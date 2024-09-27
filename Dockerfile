FROM amazoncorretto:17.0.7

COPY  target/apeh-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]
