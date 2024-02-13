package com.microservice.registrofr.client;

import com.microservice.registrofr.dto.ContactoTemDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "msvc-contacto", url = "localhost:8080/api/contacto")
public interface ContactoInfClient {

    @GetMapping("/search-contact-by-curp/{curp}")
    List<ContactoTemDTO> findActiveEmailByCurp(@PathVariable String curp);




}
