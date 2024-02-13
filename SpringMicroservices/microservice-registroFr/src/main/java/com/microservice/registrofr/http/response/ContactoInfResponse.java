package com.microservice.registrofr.http.response;

import com.microservice.registrofr.dto.ContactoTemDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.repository.query.Param;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactoInfResponse {

    private List<ContactoTemDTO> contactoTemDTOList;

}
