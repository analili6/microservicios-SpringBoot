package com.microservice.registrofr.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@Table(name = "frecuencia_Respiratoria")
@AllArgsConstructor
@NoArgsConstructor
public class FrecuenciaR {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_RegistroFr;
    private LocalDateTime fecha_hora;
    private int frecuencia_Resp;

    @Column(name ="curp")
    private String curp;

}
