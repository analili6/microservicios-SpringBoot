package com.microservice.gateway.client;

import com.microservice.gateway.dto.IdUserDTO;
import com.microservice.gateway.dto.InfanteUsrDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "msvc-infante", url = "localhost:8080/api/infante")
public interface InfanteUsrClient {
    @GetMapping("/search-by-idUsuario/{noSerie}")
    List<InfanteUsrDTO> findAllInfanteByIdInf(@PathVariable Integer idUsuario);

    @GetMapping("/search-user/{idUsuario}")
    List<IdUserDTO> findById(@PathVariable Integer idUsuario);


}
