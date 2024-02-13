package com.microservice.registrotem.client;

import com.microservice.registrotem.dto.ContactoTemDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "msvc-contacto", url = "localhost:8080/api/contacto")
public interface ContactoTemClient {
    @GetMapping("/search-contact-by-curp/{curp}")
    List<ContactoTemDto> findActiveEmailByCurp(@PathVariable String curp);



}
