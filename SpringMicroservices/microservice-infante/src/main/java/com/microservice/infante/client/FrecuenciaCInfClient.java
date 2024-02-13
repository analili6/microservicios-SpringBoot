package com.microservice.infante.client;

import com.microservice.infante.dto.FrecuenciaCDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "msvc-registrofc", url = "localhost:8080/api/frecuenciac")

public interface FrecuenciaCInfClient {
    @GetMapping("/search-by-curp/{curp}")
    List<FrecuenciaCDTO> findAllFcByCurp(@PathVariable String curp);

}
