package com.microservice.registrofc.http.response;

import com.microservice.registrofc.dto.ContactoFcDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactoFcResponse {
    private List<ContactoFcDTO> contactoFrDTOList;

}
