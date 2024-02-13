package com.microservice.registrofr.service;

import com.microservice.registrofr.dto.ContactoTemDTO;
import com.microservice.registrofr.entity.FrecuenciaR;
import com.microservice.registrofr.http.response.ContactoInfResponse;
import com.microservice.registrofr.http.response.InfanteFrResponse;


import java.util.List;

public interface IFrecuenciaRService {

    List<FrecuenciaR> findAll();
    FrecuenciaR findById(Integer id_RegistroFr);

    String save(FrecuenciaR frecuenciaR);

    List<FrecuenciaR> findByCurp(String curp);

    List<FrecuenciaR> findLastFrByCurp (String curp);

    ContactoInfResponse findEmailContacto(String curp);
    InfanteFrResponse findEdadByInfante(String curp);


}
