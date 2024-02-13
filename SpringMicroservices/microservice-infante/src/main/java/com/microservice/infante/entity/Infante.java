package com.microservice.infante.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@Table(name = "infante")
@AllArgsConstructor
@NoArgsConstructor
public class Infante {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 18, unique = true)
    private String curp;

    @Column(length = 30)
    private String nombreInfante;
    @Column(length = 60)
    private String apPaterno;
    @Column(length = 60)
    private String apMaterno;
    @Column(length = 10)
    private String sexo;
    @Column(length = 6)
    private String tipoSangre;
    private LocalDate fechanac;
    private float talla;
    private float peso;
    private String observaciones;

    @Column(name ="idUsuario", unique = true)
    private int idUsuario;


}
