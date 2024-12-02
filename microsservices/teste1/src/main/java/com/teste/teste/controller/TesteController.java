package com.teste.teste.controller;

import com.teste.teste.service.TesteService;
import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class TesteController {

    @Autowired
    private TesteService testeService;

    @Autowired
    private Tracer tracer;

    private static final Logger logger = LoggerFactory.getLogger(TesteController.class);

    @GetMapping
    public ResponseEntity<?> getProduct() {

        Span mainSpan = tracer.nextSpan().name("mainWorkflow").tag("teste", "1").start();
        try (Tracer.SpanInScope scope = tracer.withSpan(mainSpan)) {
            logger.info("Inicio da request");

            testeService.getProduct();
        }
        finally {
            logger.info("Fim da request");
            mainSpan.end();
        }





        return ResponseEntity.ok().build();
    }
}
