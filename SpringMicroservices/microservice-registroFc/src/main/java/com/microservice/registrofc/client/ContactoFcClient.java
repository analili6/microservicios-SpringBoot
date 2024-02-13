package com.microservice.registrofc.client;

import com.microservice.registrofc.dto.ContactoFcDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "msvc-contacto", url = "localhost:8080/api/contacto")
public interface ContactoFcClient {
    @GetMapping("/search-contact-by-curp/{curp}")
    List<ContactoFcDTO> findActiveEmailByCurp(@PathVariable String curp);

}
