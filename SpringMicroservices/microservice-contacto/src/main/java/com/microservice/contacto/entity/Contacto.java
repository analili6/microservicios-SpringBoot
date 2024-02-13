package com.microservice.contacto.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Entity
@Builder
@Table(name = "contacto")
@AllArgsConstructor
@NoArgsConstructor
public class Contacto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idContacto;
    @Column(length = 30)
    private String nombreContacto;
    @Column(length = 60)
    private String apPaterno;
    @Column(length = 60)
    private String apMaterno;
    @Column(length = 60)
    private String parentesco;
    @Column(length = 60)
    private String email;
    @Column(columnDefinition = "BOOLEAN")
    private boolean active;
    private String otp;
    private LocalDateTime otpGeneratedTime;

    @Column(name ="curp")
    private String curp;
}
