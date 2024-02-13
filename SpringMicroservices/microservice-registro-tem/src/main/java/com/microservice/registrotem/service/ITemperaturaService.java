package com.microservice.registrotem.service;

import com.microservice.registrotem.entity.Temperatura;
import com.microservice.registrotem.http.response.ContactoTemResponse;
import com.microservice.registrotem.http.response.InfanteTemResponse;

import java.util.List;

public interface ITemperaturaService {


    List<Temperatura> findAll();
    Temperatura findById(Integer id_RegistroTem);

     String save(Temperatura temperatura);

    List<Temperatura> findByCurp(String curp);
    List<Temperatura> findLastTempByCurp (String curp);
    ContactoTemResponse findEmailContacto(String curp);

    InfanteTemResponse findEdadByInfante(String curp);

//    List<Temperatura> findEdadInfante(String curp);

}
