package com.microservice.infante.client;

import com.microservice.infante.dto.FrecuenciaRDTO;
import com.microservice.infante.dto.TemperaturaInfDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "msvc-registrofr", url = "localhost:8080/api/frecuenciar")
public interface FrecuenciaRInfClient {
    @GetMapping("/search-by-curp/{curp}")
    List<FrecuenciaRDTO> findAllFrByCurp(@PathVariable String curp);

}
