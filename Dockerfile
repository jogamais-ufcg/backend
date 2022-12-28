FROM openjdk:17-jdk-slim
EXPOSE 8080
ADD . .
CMD java -Djava.security.egd=file:/dev/./urandon -jar ./target/ufcg-0.0.1-SNAPSHOT.jar