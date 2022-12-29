FROM openjdk:17-jdk-slim
EXPOSE 8080
ADD . .
CMD java -Djava.security.egd=file:/dev/./urandon -jar ./output/app.jar