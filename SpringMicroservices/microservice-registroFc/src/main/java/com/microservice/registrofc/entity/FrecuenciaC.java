package com.microservice.registrofc.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@Table(name = "frecuencia_Cardiaca")
@AllArgsConstructor
@NoArgsConstructor
public class FrecuenciaC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_RegistroFc;
    private LocalDateTime fecha_hora;
    private int pulsaciones;

    @Column(name ="curp")
    private String curp;
}
