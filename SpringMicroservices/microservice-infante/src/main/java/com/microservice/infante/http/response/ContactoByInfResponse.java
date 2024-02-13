package com.microservice.infante.http.response;

import com.microservice.infante.dto.ContactoInfDTO;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactoByInfResponse {

    private String nombreInfante;
    private List<ContactoInfDTO> contactoInfDTOList;


}
