package com.microservice.registrofc.service;
import com.microservice.registrofc.client.ContactoFcClient;
import com.microservice.registrofc.client.InfanteFcClient;
import com.microservice.registrofc.dto.ContactoFcDTO;
import com.microservice.registrofc.entity.FrecuenciaC;
import com.microservice.registrofc.http.response.ContactoFcResponse;
import com.microservice.registrofc.http.response.InfanteFcResponse;
import com.microservice.registrofc.persistence.FrecuenciaCRepository;
import com.microservice.registrofc.util.EmailUtil;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service

public class FrecuenciaCImpl implements IFrecuenciaCService{

    @Autowired
    private FrecuenciaCRepository frecuenciaCRepository;
    @Autowired
    private ContactoFcClient contactoFcClient;
    @Autowired
    private EmailUtil emailUtil;

    @Autowired
    private InfanteFcClient infanteFcClient;

    @Override
    public List<FrecuenciaC> findAll() {
        return (List<FrecuenciaC>) frecuenciaCRepository.findAll();
    }

    @Override
    public FrecuenciaC findById(Integer id_RegistroFc) {
        return frecuenciaCRepository.findById(id_RegistroFc).orElseThrow();
    }

    @Override
    public String save(FrecuenciaC frecuenciaC) {
        if (frecuenciaC.getPulsaciones() == 0) {
            return "El monitoreo se detuvo";
        } else {
            frecuenciaC.setFecha_hora(LocalDateTime.now());
            frecuenciaCRepository.save(frecuenciaC);

            // Obtener edad
            List<Integer> edadesEnMeses = infanteFcClient.findEdadByInfante(frecuenciaC.getCurp());


            for (Integer edadEnMeses : edadesEnMeses) {
                if (edadEnMeses >= 0 && edadEnMeses <= 3 && frecuenciaC.getPulsaciones() >= 164 && frecuenciaC.getPulsaciones() <= 169) {
                    enviarAlertaCorreo(frecuenciaC);
                } else if (edadEnMeses > 3 && edadEnMeses <= 6 && frecuenciaC.getPulsaciones() >= 159 && frecuenciaC.getPulsaciones() <= 164) {
                    enviarAlertaCorreo(frecuenciaC);
                } else if (edadEnMeses > 6 && edadEnMeses <= 9 && frecuenciaC.getPulsaciones() >= 152 && frecuenciaC.getPulsaciones() <= 157) {
                    enviarAlertaCorreo(frecuenciaC);
                } else if (edadEnMeses > 9 && edadEnMeses <= 12 && frecuenciaC.getPulsaciones() >= 145&& frecuenciaC.getPulsaciones() <= 150) {
                    enviarAlertaCorreo(frecuenciaC);
                }
            }

            return null;
        }
    }

    private void enviarAlertaCorreo(FrecuenciaC frecuenciaC) {
        // Obtener correos electrónicos activos
        List<ContactoFcDTO> contactoFcDTOList = contactoFcClient.findActiveEmailByCurp(frecuenciaC.getCurp());

        // Enviar alerta a cada contacto
        for (ContactoFcDTO contacto : contactoFcDTOList) {
            try {
                emailUtil.sendEmailAlert(contacto.getEmail(), "La Frecuencia cardiaca esta aumentando progreivamente  " + frecuenciaC.getPulsaciones());
            } catch (MessagingException e) {
                // Manejar el error de envío de correo electrónico según sea necesario
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<FrecuenciaC> findByCurp(String curp) {
        return frecuenciaCRepository.findAllFcInf(curp);

    }

    @Override
    public List<FrecuenciaC> findLastFcByCurp (String curp) {
        return frecuenciaCRepository.findLastFcByCurp(curp);
    }

    @Override
    public ContactoFcResponse findEmailContacto(String curp) {
        List<ContactoFcDTO> contactoFrDTOList = contactoFcClient.findActiveEmailByCurp(curp);
        return ContactoFcResponse.builder()
                .contactoFrDTOList(contactoFrDTOList)
                .build();
    }

    @Override
    public InfanteFcResponse  findEdadByInfante(String curp) {
        List<Integer> edadesEnMeses = infanteFcClient.findEdadByInfante(curp);
        return InfanteFcResponse.builder()
                .edadesEnMeses(edadesEnMeses)
                .build();
    }


}
