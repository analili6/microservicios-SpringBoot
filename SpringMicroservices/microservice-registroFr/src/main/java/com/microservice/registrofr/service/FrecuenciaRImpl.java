package com.microservice.registrofr.service;
import com.microservice.registrofr.client.ContactoInfClient;
import com.microservice.registrofr.client.InfanteFrClient;
import com.microservice.registrofr.dto.ContactoTemDTO;
import com.microservice.registrofr.entity.FrecuenciaR;
import com.microservice.registrofr.http.response.ContactoInfResponse;
import com.microservice.registrofr.http.response.InfanteFrResponse;
import com.microservice.registrofr.persistence.FrecuenciaRRepository;
import com.microservice.registrofr.util.EmailUtil;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class FrecuenciaRImpl implements IFrecuenciaRService{
@Autowired
    private FrecuenciaRRepository frecuenciaRRepository;
@Autowired
   private ContactoInfClient contactoInfClient;

    @Autowired
    private InfanteFrClient infanteFrClientnt;


   @Autowired
   private EmailUtil emailUtil;

    @Override
    public List<FrecuenciaR> findAll() {
        return (List<FrecuenciaR>) frecuenciaRRepository.findAll();
    }

    @Override
    public FrecuenciaR findById(Integer id_RegistroFr) {
        return frecuenciaRRepository.findById(id_RegistroFr).orElseThrow();
    }

    @Override
    public List<FrecuenciaR> findByCurp(String curp) {
        return frecuenciaRRepository.findAllFrInf(curp);
    }

    @Override
    public List<FrecuenciaR> findLastFrByCurp (String curp) {
        return frecuenciaRRepository.findLastFrByCurp(curp);
    }

//    @Override
//    public String save(FrecuenciaR frecuenciaR) {
//        if (frecuenciaR.getFrecuencia_Resp() == 0) {
//            return "El monitoreo se detuvo";
//        } else {
//            frecuenciaR.setFecha_hora(LocalDateTime.now());
//            frecuenciaRRepository.save(frecuenciaR);
//
//            // Obtener la edad en meses del bebé
//            List<Integer> edadesEnMeses = infanteFrClientnt.findEdadByInfante(frecuenciaR.getCurp());
//
//            // Verificar las condiciones según la edad
//            for (Integer edadEnMeses : edadesEnMeses) {
//                if (edadEnMeses >= 0 && edadEnMeses <= 1 && frecuenciaR.getFrecuencia_Resp() >= 95) {
//                    enviarAlertaCorreo(frecuenciaR);
//                } else if (edadEnMeses > 1 && edadEnMeses <= 3 && frecuenciaR.getFrecuencia_Resp() >= 40) {
//                    enviarAlertaCorreo(frecuenciaR);
//                } else if (edadEnMeses > 3 && edadEnMeses <= 6 && frecuenciaR.getFrecuencia_Resp() >= 35) {
//                    enviarAlertaCorreo(frecuenciaR);
//                } else if (edadEnMeses > 12 && edadEnMeses <= 24 && frecuenciaR.getFrecuencia_Resp() >= 30) {
//                    enviarAlertaCorreo(frecuenciaR);
//                }
//            }
//
//            return null;
//        }
//    }
//
//    private void enviarAlertaCorreo(FrecuenciaR frecuenciaR) {
//        // Obtener correos electrónicos activos
//        List<ContactoTemDTO> contactoTemDTODTOList = contactoInfClient.findActiveEmailByCurp(frecuenciaR.getCurp());
//
//        // Enviar alerta a cada contacto
//        for (ContactoTemDTO contacto : contactoTemDTODTOList) {
//            try {
//                emailUtil.sendEmailAlert(contacto.getEmail(), "La Frecuencia respiratoria esta aumentando progreivamente  " + frecuenciaR.getFrecuencia_Resp()
//                );
//            } catch (MessagingException e) {
//                // Manejar el error de envío de correo electrónico según sea necesario
//                e.printStackTrace();
//            }
//        }
//    }
 // ewste no ocupa la edad
    @Override
    public String save(FrecuenciaR frecuenciaR) {
        if (frecuenciaR.getFrecuencia_Resp() == 0) {
            return "El monitoreo se detuvo";
        } else {
            frecuenciaR.setFecha_hora(LocalDateTime.now());
            frecuenciaRRepository.save(frecuenciaR);

            if (frecuenciaR.getFrecuencia_Resp() >= 65 && frecuenciaR.getFrecuencia_Resp() <= 70) {
                enviarAlertaCorreo(frecuenciaR, "Disminución grave de saturación de oxígeno");
            } else if (frecuenciaR.getFrecuencia_Resp() == 80) {
                enviarAlertaCorreo(frecuenciaR, "Disminución leve de saturación de oxígeno");
            } else if (frecuenciaR.getFrecuencia_Resp() == 90) {
                enviarAlertaCorreo(frecuenciaR, "Saturación de oxígeno normal");
            }

            return null;
        }
    }

    private void enviarAlertaCorreo(FrecuenciaR frecuenciaR, String mensaje) {
        // Obtener correos electrónicos activos
        List<ContactoTemDTO> contactoTemDTODTOList = contactoInfClient.findActiveEmailByCurp(frecuenciaR.getCurp());

        // Enviar alerta a cada contacto
        for (ContactoTemDTO contacto : contactoTemDTODTOList) {
            try {
                emailUtil.sendEmailAlert(contacto.getEmail(), mensaje + ". Oxigenacion: " + frecuenciaR.getFrecuencia_Resp());
            } catch (MessagingException e) {
                // Manejar el error de envío de correo electrónico según sea necesario
                e.printStackTrace();
            }
        }
    }





    @Override
    public ContactoInfResponse findEmailContacto(String curp) {
        List<ContactoTemDTO> contactoTemDTOList = contactoInfClient.findActiveEmailByCurp(curp);
        return ContactoInfResponse.builder()
                .contactoTemDTOList(contactoTemDTOList)
                .build();
    }

    @Override
    public InfanteFrResponse  findEdadByInfante(String curp) {
        List<Integer> edadesEnMeses = infanteFrClientnt.findEdadByInfante(curp);
        return InfanteFrResponse.builder()
                .edadesEnMeses(edadesEnMeses)
                .build();
    }

}
