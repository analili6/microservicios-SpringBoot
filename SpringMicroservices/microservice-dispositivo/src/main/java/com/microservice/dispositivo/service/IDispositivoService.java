package com.microservice.dispositivo.service;

import com.microservice.dispositivo.entity.Dispositivo;
import com.microservice.dispositivo.http.response.UsuarioByDisResponse;

import java.util.List;

public interface IDispositivoService {
    List<Dispositivo> findAll();
    Dispositivo findById (String noSerie);
    void save (Dispositivo dispositivo);
    UsuarioByDisResponse findDisByUsr (String noSerie);




}
