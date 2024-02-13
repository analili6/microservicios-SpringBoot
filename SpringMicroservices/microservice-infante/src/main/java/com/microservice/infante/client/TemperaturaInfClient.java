package com.microservice.infante.client;

import com.microservice.infante.dto.TemperaturaInfDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@FeignClient(name = "msvc-registrotem", url = "localhost:8080/api/temperatura")
public interface TemperaturaInfClient {
    @GetMapping("/search-by-curp/{curp}")
    List<TemperaturaInfDTO> findAllTemByCurp(@PathVariable String curp);

}
