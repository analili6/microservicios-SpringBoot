package com.microservice.infante.service;

import com.microservice.infante.dto.InfanteDto;
import com.microservice.infante.entity.Infante;
import com.microservice.infante.http.response.ContactoByInfResponse;
import com.microservice.infante.http.response.FrecCByInfResponse;
import com.microservice.infante.http.response.FrecRByInfResponse;
import com.microservice.infante.http.response.TemperaturaByInfResponse;

import java.time.LocalDate;
import java.util.List;

public interface IInfanteService {
    List<Infante> findAll();
    Infante findByCurp (String curp);
//    void save (Infante infante);

    //List<Infante> findByIdFrecuenciar(Integer id_fr);
    //List<Infante> findByIdFrecuenciac(Integer id_fc);

    //List<Infante> findByIdTemperatura(Integer id_tem);
    void updateInfante(String curp, InfanteDto infanteDto);
    ContactoByInfResponse findContactoByInf (String curp);
    TemperaturaByInfResponse findTemByInf (String curp);
    FrecCByInfResponse findFcByInf (String curp);
    FrecRByInfResponse findFrByInf (String curp);
    List<Infante> findByUsuario(Integer idUsuario);
    List<Infante> findByIdUsuario(Integer idUsuario);

    public List<Integer> findEdadEnMeses(String curp);
    String save (Infante infante);



}
