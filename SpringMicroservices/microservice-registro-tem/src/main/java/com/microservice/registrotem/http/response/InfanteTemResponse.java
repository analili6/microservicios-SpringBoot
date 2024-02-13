package com.microservice.registrotem.http.response;

import com.microservice.registrotem.dto.ContactoTemDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InfanteTemResponse {
    private List<Integer> edadesEnMeses;

}
