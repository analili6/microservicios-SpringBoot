package com.microservice.infante.http.response;

import com.microservice.infante.dto.ContactoInfDTO;
import com.microservice.infante.dto.TemperaturaInfDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TemperaturaByInfResponse {
    private String nombreInfante;
    private LocalDate fechanac;
    private List<TemperaturaInfDTO> temperaturaInfDTOList;

//    private List<TemperaturaInfDTO> temperaturaInfDTOList;

}
