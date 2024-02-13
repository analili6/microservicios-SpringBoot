package com.microservice.gateway.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegenerateOtpRequest {
    private String email;
}
