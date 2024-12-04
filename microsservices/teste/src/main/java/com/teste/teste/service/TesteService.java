package com.teste.teste.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.teste.teste.client.AuthTeste1Client;
import com.teste.teste.client.Teste1Client;
import com.teste.teste.model.Product;
import com.teste.teste.repositories.ProductRepository;
import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class TesteService {

    @Value("${api.teste1.clientId}")
    private String clientIdTeste1Api;

    @Value("${api.teste1.clientSecret}")
    private String clientSecretTeste1Api;

    @Value("${api.teste1.grantType}")
    private String grantTypeTeste1Api;

    @Autowired
    private AuthTeste1Client authTeste1Client;

    @Autowired
    private Teste1Client teste1Client;

    @Autowired
    private ProductRepository productRepository;



    @Autowired
    private Tracer tracer;

    private static final Logger logger = LoggerFactory.getLogger(TesteService.class);


    public void getProduct() {
        Span chamadaTeste1 = tracer.nextSpan().name("chamada Teste 1").start();
        try (Tracer.SpanInScope ignored = tracer.withSpan(chamadaTeste1)) {
            logger.info("Chamando Teste 1");


            Map<String, String> body = new HashMap<>();
            body.put("client_id", clientIdTeste1Api);
            body.put("client_secret", clientSecretTeste1Api);
            body.put("grant_type", grantTypeTeste1Api);

            ResponseEntity<JsonNode> tokenRB =
                    authTeste1Client.auth(body);

            String token = "";

            if (tokenRB.getStatusCode().value() == 200 && tokenRB.hasBody() &&
                    tokenRB.getBody().has("access_token")) {
                token = tokenRB.getBody().path("access_token").asText("");

            }

            ResponseEntity<?> product = teste1Client.getProduct("Bearer " + token);

            logger.info("status code {}", product.getStatusCode());

            Optional<Product> productClazzOpt = productRepository.findById("1");

            productClazzOpt
                    .ifPresent(p -> {
                        logger.info("Produto id={} name={}", p.id(), p.name());
            });
        } catch (Exception e) {
            logger.error("Erro na chamada", e);
        }
        finally {
            chamadaTeste1.end();
        }


    }
}
