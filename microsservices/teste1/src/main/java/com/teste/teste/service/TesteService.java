package com.teste.teste.service;

import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TesteService {

    @Autowired
    private Tracer tracer;

    private static final Logger logger = LoggerFactory.getLogger(TesteService.class);


    public void getProduct() {
        Span chamadaTeste1 = tracer.nextSpan().name("chamada Teste 1").start();
        try (Tracer.SpanInScope scope = tracer.withSpan(chamadaTeste1)) {
            logger.info("Chamando Mock");

            logger.info("Mock chamado");

        }
        finally {
            chamadaTeste1.end();
        }


    }
}
