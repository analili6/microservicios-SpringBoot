package com.microservice.registrotem.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "msvc-infante", url = "localhost:8080/api/infante")
public interface InfanteTemClient {
    @GetMapping("/edad-en-meses/{curp}")
    List<Integer> findEdadByInfante(@PathVariable String curp);
}
