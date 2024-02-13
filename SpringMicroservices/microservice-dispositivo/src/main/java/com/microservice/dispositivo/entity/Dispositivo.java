package com.microservice.dispositivo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table(name = "dispositivo")
@AllArgsConstructor
@NoArgsConstructor

public class Dispositivo {
    @Id
    @Column(length = 8, unique = true)
    private String noSerie;

    @Column(length = 30)
    private String nombre;
}
