package com.microservice.contacto.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerifyAccountRequest {
    private String email;
    private String otp;
}
