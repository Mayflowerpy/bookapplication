FROM eclipse-temurin:17-jre-alpine
WORKDIR /usr/local/app
ADD target/bookapplication-0.0.1-SNAPSHOT.jar app.jar
CMD java -jar ./app.jar