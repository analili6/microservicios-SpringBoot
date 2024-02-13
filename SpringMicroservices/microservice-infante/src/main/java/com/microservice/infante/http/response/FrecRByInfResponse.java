package com.microservice.infante.http.response;

import com.microservice.infante.dto.FrecuenciaCDTO;
import com.microservice.infante.dto.FrecuenciaRDTO;
import com.microservice.infante.dto.TemperaturaInfDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FrecRByInfResponse {
    private String nombreInfante;
    private List<FrecuenciaRDTO> frecuenciaRDTOList;
}
