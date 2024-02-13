package com.microservice.registrotem.service;

import com.microservice.registrotem.client.ContactoTemClient;
import com.microservice.registrotem.client.InfanteTemClient;
import com.microservice.registrotem.dto.ContactoTemDto;

import com.microservice.registrotem.entity.Temperatura;
import com.microservice.registrotem.http.response.ContactoTemResponse;
import com.microservice.registrotem.http.response.InfanteTemResponse;
import com.microservice.registrotem.persistence.TemperaturaRepository;
import com.microservice.registrotem.util.EmailUtil;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service

public class TemperaturaImpl  implements ITemperaturaService{

    @Autowired
    private TemperaturaRepository temperaturaRepository;

    @Autowired
    private InfanteTemClient infanteTemClient;

    @Autowired
    private EmailUtil emailUtil;
    @Autowired
    private ContactoTemClient contactoTemClient;
    @Override
    public List<Temperatura> findAll() {
        return (List<Temperatura>) temperaturaRepository.findAll();
    }

    @Override
    public Temperatura findById(Integer id_RegistroTem) {
        return temperaturaRepository.findById(id_RegistroTem).orElseThrow();
    }

    @Override
    public String save(Temperatura temperatura) {
        if (temperatura.getTemperatura() == 0) {
            return "El monitoreo se detuvo";
        } else {
            temperatura.setFecha_hora(LocalDateTime.now());
            temperaturaRepository.save(temperatura);

            if (temperatura.getTemperatura() >= 36.5 && temperatura.getTemperatura() <= 38.0) {
                // Obtener correos electrónicos activos
                List<ContactoTemDto> contactoTemDTOList = contactoTemClient.findActiveEmailByCurp(temperatura.getCurp());

                // Enviar alerta a cada contacto
                for (ContactoTemDto contacto : contactoTemDTOList) {
                    try {
                        emailUtil.sendEmailAlert(contacto.getEmail(), "La Temperatura subio progresivamente  " + temperatura.getTemperatura());
                    } catch (MessagingException e) {
                        return "Error al enviar el correo electrónico";
                    }
                }
            }

            return null;
        }
    }

    @Override
    public List<Temperatura> findByCurp(String curp) {
        return temperaturaRepository.findAllTemInf(curp);
    }
    @Override
    public List<Temperatura> findLastTempByCurp (String curp) {
        return temperaturaRepository.findLastTemperatureByCurp(curp);
    }

    @Override
    public ContactoTemResponse findEmailContacto(String curp) {
        List<ContactoTemDto> contactoTemDtoList = contactoTemClient.findActiveEmailByCurp(curp);
        return ContactoTemResponse.builder()
                .contactoTemDtoList(contactoTemDtoList)
                .build();
    }

    @Override
    public InfanteTemResponse findEdadByInfante(String curp) {
        List<Integer> edadesEnMeses = infanteTemClient.findEdadByInfante(curp);
        return InfanteTemResponse.builder()
                .edadesEnMeses(edadesEnMeses)
                .build();
    }


}
