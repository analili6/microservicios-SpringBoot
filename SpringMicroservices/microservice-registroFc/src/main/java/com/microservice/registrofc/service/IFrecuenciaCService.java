package com.microservice.registrofc.service;

import com.microservice.registrofc.entity.FrecuenciaC;
import com.microservice.registrofc.http.response.ContactoFcResponse;
import com.microservice.registrofc.http.response.InfanteFcResponse;


import java.util.List;

public interface IFrecuenciaCService {

    List<FrecuenciaC> findAll();
    FrecuenciaC findById(Integer id_RegistroFc);

    String save(FrecuenciaC frecuenciaC);

    List<FrecuenciaC> findByCurp(String curp);

    List<FrecuenciaC> findLastFcByCurp (String curp);

    ContactoFcResponse findEmailContacto(String curp);

    InfanteFcResponse findEdadByInfante(String curp);
}
