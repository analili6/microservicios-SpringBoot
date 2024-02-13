package com.microservice.contacto.service;

import com.microservice.contacto.dto.RegisterDto;
import com.microservice.contacto.dto.UpdateDto;
import com.microservice.contacto.entity.Contacto;
import com.microservice.contacto.persistence.ContactoRepository;
import com.microservice.contacto.util.EmailUtil;
import com.microservice.contacto.util.OtpUtil;
import io.micrometer.common.util.StringUtils;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ContactoServiceImpl implements  IContactoService{
    @Autowired
    private OtpUtil otpUtil;
    @Autowired
    private EmailUtil emailUtil;
    @Autowired
    private ContactoRepository contactoRepository;


    @Override
    public List<Contacto> findAll() {
        return (List<Contacto>) contactoRepository.findAll();
    }

    @Override
    public Contacto findById(Integer idContacto) {
        return contactoRepository.findById(idContacto).orElseThrow();
    }

    @Override
    public void deleteById(Integer idContacto) {
        Contacto contacto = contactoRepository.findById(idContacto)
                .orElseThrow(() -> new RuntimeException("Contacto no encontrado "));
            contacto.setActive(false);
            contactoRepository.save(contacto);

        }

    public void enableContact(Integer idContacto) {
        Contacto contacto = contactoRepository.findById(idContacto)
                .orElseThrow(() -> new RuntimeException("Contacto no encontrado "));
        contacto.setActive(true);
        contactoRepository.save(contacto);
    }

    @Override
    public void save(Contacto contacto) {
        contactoRepository.save(contacto);
    }

    @Override
    public List<Contacto> findByCurp(String curp) {
        return contactoRepository.findAllContactoInf(curp);
    }

    @Override
    public String regenerateOtp(String email) {
                Contacto  contacto = contactoRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Contacto no encontrado con este correo electrónico: " + email));
        String otp = otpUtil.generateOtp();
        try {
            emailUtil.sendOtpEmail(email, otp);
        } catch (MessagingException e) {
            throw new RuntimeException("No se puede enviar el codigo , inténtelo de nuevo");
        }
        contacto.setOtp(otp);
        contacto.setOtpGeneratedTime(LocalDateTime.now());
        contactoRepository.save(contacto);
        return "Correo electrónico enviado... Verifique la cuenta dentro de 1 minuto";
    }

    @Override
    public String verifyAccount(String email, String otp) {
        Contacto contacto = contactoRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Contacto no encontrado con este correo electrónico: " + email));
        if (contacto.getOtp().equals(otp) && Duration.between(contacto.getOtpGeneratedTime(),
                LocalDateTime.now()).getSeconds() < (1 * 300)) {
            contacto.setActive(true);
            contactoRepository.save(contacto);
            return "Cuenta verificada , ahyora ya puede recibir Alertas";
        }
        return "Por favor, regenere el codigo  e inténtelo de nuevo";
        }

    @Override
    public String register(RegisterDto registerDTO) {
        // Validación de entrada
        if (registerDTO == null || StringUtils.isEmpty(registerDTO.getEmail())) {
            return ("Datos de registro no válidos. Se requiere correo electrónico");
        }

        // Verificar si el correo electrónico ya está registrado y activo para el mismo infante
        List<String> activeEmails = contactoRepository.findActiveEmailByCurp(registerDTO.getCurp());
        if (activeEmails.contains(registerDTO.getEmail())) {
            return ("El correo electrónico ya está registrado y activo para este infante.");
        }

//         Verificar si el correo electrónico ya está registrado
        if (contactoRepository.existsByEmail(registerDTO.getEmail())) {
            return ("Correo electrónico ya registrado. Utilice una dirección de correo electrónico diferente.");
        }

        String otp = otpUtil.generateOtp();
        try {
            // Envío del correo electrónico con el OTP
            emailUtil.sendOtpEmail(registerDTO.getEmail(), otp);
        } catch (Exception e) {
            return ("No se puede enviar el código. Por favor, inténtelo de nuevo.");
        }

        // Creación y guardado del usuario
        Contacto contacto = new Contacto();
        contacto.setNombreContacto(registerDTO.getNombreContacto());
        contacto.setApPaterno(registerDTO.getApPaterno());
        contacto.setApMaterno(registerDTO.getApMaterno());
        contacto.setParentesco(registerDTO.getParentesco());
        contacto.setCurp(registerDTO.getCurp());
        contacto.setEmail(registerDTO.getEmail());
        contacto.setOtp(otp);
        contacto.setOtpGeneratedTime(LocalDateTime.now());
        contacto.setActive(false);
        contactoRepository.save(contacto);

        return "El registro de contacto se ha realizado correctamente para " + contacto.getNombreContacto() +
                ". Se ha enviado un código a su correo electrónico para verificar la cuenta.";
    }



    @Override
    public String update(Integer idContacto, UpdateDto updateDto) {
        // Validación de entrada
        if (idContacto == null) {
            return ("ID de contacto no válido");
        }

        // Buscar el contacto por ID
        Contacto contacto = contactoRepository.findById(idContacto)
                .orElseThrow(() -> new RuntimeException("Contacto no encontrado con ID: " + idContacto));

        // Verificar si el correo electrónico ha cambiado
        if (!contacto.getEmail().equals(updateDto.getEmail())) {
            // El correo electrónico ha cambiado, verificar si ya está registrado y activo para el mismo infante
            List<String> activeEmails = contactoRepository.findActiveEmailByCurp(contacto.getCurp());
            if (activeEmails.contains(updateDto.getEmail())) {
                return ("El nuevo correo electrónico ya está registrado y activo para este infante.");
            }

            // Generar y enviar el nuevo OTP al nuevo correo electrónico
            String newOtp = otpUtil.generateOtp();
            try {
                emailUtil.sendOtpEmail(updateDto.getEmail(), newOtp);

                // Actualizar el contacto
                contacto.setEmail(updateDto.getEmail());
                contacto.setOtp(newOtp);
                contacto.setOtpGeneratedTime(LocalDateTime.now());
                contacto.setActive(false); // Cambiar a inactivo hasta la verificación

//                return "Revise su nuevo correo electrónico para verificar la nueva cuenta";
            } catch (MessagingException e) {
                throw new RuntimeException("No se puede enviar el código, inténtelo de nuevo");
            }
        }

        contacto.setNombreContacto(updateDto.getNombreContacto());
        contacto.setApPaterno(updateDto.getApPaterno());
        contacto.setApMaterno(updateDto.getApMaterno());
        contacto.setParentesco(updateDto.getParentesco());
        contacto.setCurp(updateDto.getCurp());

        contactoRepository.save(contacto);

        return "Contacto actualizado correctamente para " + contacto.getNombreContacto();
    }


    @Override
    public List<String> findActiveEmailByCurp(String curp) {
        return contactoRepository.findActiveEmailByCurp(curp);
    }



}
