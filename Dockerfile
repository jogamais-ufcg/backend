FROM openjdk:17-jdk-slim

COPY . .

RUN ./mvnw clean package -Dspring.profiles.active=dev -Dmaven.test.skip spring-boot:repackage -X

EXPOSE 8080

CMD java -jar target/ufcg-0.0.1-SNAPSHOT.jar