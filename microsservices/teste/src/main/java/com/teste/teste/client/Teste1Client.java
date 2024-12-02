package com.teste.teste.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "teste1Client", url = "http://localhost:5001")
public interface Teste1Client {

    @GetMapping("/products")
    ResponseEntity<?> getProduct(@RequestHeader("Authorization") String auth);
}
