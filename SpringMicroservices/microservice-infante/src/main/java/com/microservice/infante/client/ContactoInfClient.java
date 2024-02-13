package com.microservice.infante.client;

import com.microservice.infante.dto.ContactoInfDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "msvc-contacto", url = "localhost:8080/api/contacto")
public interface ContactoInfClient {
    @GetMapping("/search-by-curp/{curp}")
    List<ContactoInfDTO> findAllContactoByCurp(@PathVariable String curp);

}
