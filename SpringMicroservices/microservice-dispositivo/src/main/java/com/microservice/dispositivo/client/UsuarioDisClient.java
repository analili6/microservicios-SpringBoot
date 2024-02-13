package com.microservice.dispositivo.client;

import com.microservice.dispositivo.dto.UsuarioDisDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "msvc-gateway", url = "localhost:8080/api/user")
public interface  UsuarioDisClient {

    @GetMapping("/search-by-noserie/{noSerie}")
    List<UsuarioDisDTO> findAllUsuarioByNoSerie(@PathVariable String noSerie);

}
