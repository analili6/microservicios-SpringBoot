package com.microservice.gateway.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDto {

    private String nombreUsuario;
    private String email;
    private String password;
}
