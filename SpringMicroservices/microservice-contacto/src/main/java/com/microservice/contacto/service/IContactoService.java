package com.microservice.contacto.service;

import com.microservice.contacto.dto.RegisterDto;
import com.microservice.contacto.dto.UpdateDto;
import com.microservice.contacto.entity.Contacto;

import java.util.List;

public interface IContactoService {
    List<Contacto> findAll();
    Contacto findById (Integer idContacto);
    void save (Contacto contacto);

    void deleteById (Integer idContacto);

    List<Contacto> findByCurp(String curp);

    String regenerateOtp (String email);
    String verifyAccount(String email, String otp);

    String register(RegisterDto registerDTO);
    String update(Integer idContacto, UpdateDto updateDto);

//    TemperaturaByContResponse findTemByInf (Integer idContacto, String curp);

     List<String> findActiveEmailByCurp(String curp);
    void enableContact(Integer idContacto);

}
