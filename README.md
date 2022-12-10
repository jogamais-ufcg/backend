# Backend

## Como fazer build?
O seguinte comando gera um .jar na pasta target
```bash
./mvnw clean package -Dmaven.test.skip spring-boot:repackage -X
```
você pode executar o jar gerado com:
```bash
java -jar target/ufcg-0.0.1-SNAPSHOT.jar
```
