package com.microservice.gateway.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;
    @Column(length = 30)
    private String nombreUsuario;
    @Column(unique = true ,length = 60)
    private String email;
    @Column(length = 60)
    private String password;
    @Column(columnDefinition = "BOOLEAN")
    private boolean active;
    private String otp;
    private LocalDateTime otpGeneratedTime;

    @Column(name ="noSerie")
    private String noSerie;

}
