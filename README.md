Repositório com uma POC integrando 2 APIs Java 21 com SpringBoot 3.3.6 com as imagens Docker Keycloak, Prometheus, Grafana e Jaeger.


Para subir as imagens:
```
docker-compose up -d
```

Para desligar as imagens:
```
docker-compose down
```

Para buildar e rodar a aplicação teste:
```
cd microsservices/teste
./gradlew build
./gradlew bootRun
```

Para buildar e rodar a aplicação teste1:
```
cd microsservices/teste1
./gradlew build
./gradlew bootRun
```


cURLS das chamadas

1) Autenticação:

```
curl --location 'http://localhost:8080/realms/myrealm/protocol/openid-connect/token' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'client_id=teste-app' \
--data-urlencode 'client_secret=secret123' \
--data-urlencode 'grant_type=client_credentials'
```

2) /products da API teste (possui autenticação)
```
curl --location 'http://localhost:5000/products' \
--header 'Authorization: Bearer token'
```

3) Liveness da API teste
```
curl --location 'http://localhost:5000/actuator/health/liveness'
```

4) Rediness da API teste
```
curl --location 'http://localhost:5000/actuator/health/readiness'
```

5) Prometheus da API teste
```
curl --location 'http://localhost:5000/actuator/prometheus'
```

Acesso das UIs da imagens Dockers:

1) Keycloak: http://localhost:8080

Usuário: admin

Senha: admin

2) Jaeger: http://localhost:16686

3) Prometheus: http://localhost:9090

4) Grafana: http://localhost:3000

Usuário: admin

Senha: admin

