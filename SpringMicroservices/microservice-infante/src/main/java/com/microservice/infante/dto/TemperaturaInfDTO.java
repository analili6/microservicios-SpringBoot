package com.microservice.infante.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TemperaturaInfDTO {
    private String nombreInfante;
    private String curp;
    private LocalDate fechanac;


}
