package com.microservice.gateway.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerifyAccountRequest {
    private String email;
    private String otp;
}
