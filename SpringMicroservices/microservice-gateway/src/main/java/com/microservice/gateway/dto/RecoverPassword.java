package com.microservice.gateway.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RecoverPassword {
    private String email;
    private String otp;
    private String newPassword;
}
