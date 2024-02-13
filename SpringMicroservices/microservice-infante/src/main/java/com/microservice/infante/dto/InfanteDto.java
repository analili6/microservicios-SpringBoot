package com.microservice.infante.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Setter
public class InfanteDto {
    private String nombreInfante;
    private String apPaterno;
    private String apMaterno;
    private String sexo;
    private String tipoSangre;
    private LocalDate fechanac;
    private float talla;
    private float peso;
    private String observaciones;
//    private int idUsuario;
}
