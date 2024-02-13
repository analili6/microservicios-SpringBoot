package com.microservice.dispositivo.http.response;

import com.microservice.dispositivo.dto.UsuarioDisDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioByDisResponse {
    private String nombreUsuario;
    private List<UsuarioDisDTO> usuarioDisDTOList;

}
