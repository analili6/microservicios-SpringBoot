package com.microservice.registrotem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@Table(name = "temperatura")
@AllArgsConstructor
@NoArgsConstructor
public class    Temperatura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_RegistroTem;
    private LocalDateTime fecha_hora;
    private float temperatura;
    @Column(name ="curp")
    private String curp;
}
