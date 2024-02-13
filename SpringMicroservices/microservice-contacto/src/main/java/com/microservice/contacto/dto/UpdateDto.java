package com.microservice.contacto.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class UpdateDto {

    private String nombreContacto;
    private String apPaterno;
    private String apMaterno;
    private String parentesco;
    private String email;
    private boolean active;
    private String otp;
    private LocalDateTime otpGeneratedTime;
    private String curp;
}
