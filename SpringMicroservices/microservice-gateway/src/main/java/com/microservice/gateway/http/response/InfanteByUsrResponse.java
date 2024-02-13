package com.microservice.gateway.http.response;

import com.microservice.gateway.dto.InfanteUsrDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InfanteByUsrResponse {

    private String nombreInfante;
    private List<InfanteUsrDTO> infanteUsrDTOList;
}
