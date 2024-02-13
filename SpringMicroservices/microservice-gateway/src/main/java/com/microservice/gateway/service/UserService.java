package com.microservice.gateway.service;

import com.microservice.gateway.client.InfanteUsrClient;
import com.microservice.gateway.dto.*;
import com.microservice.gateway.entity.User;
import com.microservice.gateway.http.response.IdByEmailResponse;
import com.microservice.gateway.http.response.IdUserResponse;
import com.microservice.gateway.http.response.InfanteByUsrResponse;
import com.microservice.gateway.repository.UserRepository;
import com.microservice.gateway.util.EmailUtil;
import com.microservice.gateway.util.OtpUtil;
import io.micrometer.common.util.StringUtils;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private OtpUtil otpUtil;
    @Autowired
    private EmailUtil emailUtil;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InfanteUsrClient infanteUsrClient;

    public String register(RegisterDto registerDTO) {
        // Validación de entrada
        if (registerDTO == null || StringUtils.isEmpty(registerDTO.getEmail()) || StringUtils.isEmpty(registerDTO.getPassword())) {
            throw new IllegalArgumentException("Datos de registro no válidos. Se requiere correo electrónico y contraseña.");
        }

        // Verificar si el correo electrónico ya está registrado
        if (userRepository.existsByEmail(registerDTO.getEmail())) {
            throw new RuntimeException("Correo electrónico ya registrado. Utilice una dirección de correo electrónico diferente.");
        }

        String otp = otpUtil.generateOtp();
        try {
            // Envío del correo electrónico con el OTP
            emailUtil.sendOtpEmail(registerDTO.getEmail(), otp);
        } catch (Exception e) {
            throw new RuntimeException("No se puede enviar el codigo. Por favor, inténtelo de nuevo.", e);
        }

        // Creación y guardado del usuario
        User user = new User();
        user.setNombreUsuario(registerDTO.getNombreUsuario());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());
        user.setOtp(otp);
        user.setOtpGeneratedTime(LocalDateTime.now());
        userRepository.save(user);

        // Mensaje de retorno informativo
        return "El registro de usuario se ha realizado correctamente para " + user.getNombreUsuario()+ "Revisa tu correo";
    }


    public String verifyAccount(String email, String otp) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con este correo electrónico: " + email));
        if (user.getOtp().equals(otp) && Duration.between(user.getOtpGeneratedTime(),
                LocalDateTime.now()).getSeconds() < (1 * 180)) {
            user.setActive(true);
            userRepository.save(user);
            return "Cuenta verificada , puede iniciar sesión";
        }
        return "Por favor, regenere el codigo  e inténtelo de nuevo";
    }

    public String regenerateOtp(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con este correo electrónico: " + email));
        String otp = otpUtil.generateOtp();
        try {
            emailUtil.sendOtpEmail(email, otp);
        } catch (MessagingException e) {
            throw new RuntimeException("No se puede enviar el codigo , inténtelo de nuevo");
        }
        user.setOtp(otp);
        user.setOtpGeneratedTime(LocalDateTime.now());
        userRepository.save(user);
        return "Correo electrónico enviado... Verifique la cuenta dentro de 1 minuto";
    }


    public String login(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(
                        () -> new RuntimeException("Usuario no encontrado con este correo electrónico: " + loginDto.getEmail()));
        if (!loginDto.getPassword().equals(user.getPassword())) {
            return "La contraseña es incorrecta";
        } else if (!user.isActive()) {
            return "Tu cuenta no está verificada";
        }
//        return "Inicio de sesión exitoso";
        return "Inicio de sesión exitoso";
    }

    public String forgotPassword(ForgotPassword forgotPassword) {
        User user = userRepository.findByEmail(forgotPassword.getEmail())
                .orElseThrow(
                        () -> new RuntimeException("User not found with this email: " + forgotPassword.getEmail()));
        String otp = otpUtil.generateOtp();
        try {
            emailUtil.sendSetPasswordEmail(forgotPassword.getEmail(), otp);
        } catch (MessagingException e) {
            throw new RuntimeException("Unable to set password email please try again");
        }
        user.setOtp(otp);
        user.setOtpGeneratedTime(LocalDateTime.now());
        userRepository.save(user);
        return "Please check your email to set new password to your account";
    }


    public String setPassword(RecoverPassword recoverPassword) {
        User user = userRepository.findByEmail(recoverPassword.getEmail())
                .orElseThrow(
                        () -> new RuntimeException("Usuario no encontrado con este correo electrónico: " + recoverPassword.getEmail()));
        if (user.getOtp().equals(recoverPassword.getOtp()) && Duration.between(user.getOtpGeneratedTime(),
                LocalDateTime.now()).getSeconds() < (1 * 60)) {
            user.setPassword(recoverPassword.getNewPassword());
            userRepository.save(user);
            return "Nueva contraseña actualizada , inicie sesion ";
        }
        return "Por favor, regenere el codigo  e inténtelo de nuevo";
    }



    public List<User> findAllUserDis(String noSerie) {
        return userRepository.findAllUserDis(noSerie);
    }
    public InfanteByUsrResponse findUsrByInf(Integer idUsuario) {
        //User user = userRepository.findById(idUsuario).orElse(new User());
        User user = userRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con este id"));

        List<InfanteUsrDTO> infanteUsrDTOList = infanteUsrClient.findAllInfanteByIdInf(idUsuario);

        return InfanteByUsrResponse.builder()
                .nombreInfante(user.getNombreUsuario())
                .infanteUsrDTOList(infanteUsrDTOList)
                .build();
    }

    //modificacion 16/12/2023
    public IdByEmailResponse  findIdByEmail(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con este correo electrónico: " + email));

        return IdByEmailResponse.builder()
                .idUsuario(user.getIdUsuario())
                .build();
    }


    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }


    public IdUserResponse findById(Integer idUsuario) {
        // Verificar si el usuario existe antes de intentar recuperarlo
        if (!userRepository.existsById(idUsuario)) {
            throw new RuntimeException("Usuario no encontrado con este id: " + idUsuario);
        }

        User user = userRepository.findById(idUsuario).orElse(null);

        // Llamar al cliente para obtener información de infante
        List<IdUserDTO> idUserDTOList = infanteUsrClient.findById(idUsuario);

        return IdUserResponse.builder()
                .idUserDTOList(idUserDTOList)
                .build();


    }

    public boolean existeUsuario(Integer idUsuario) {
        Optional<User> userOptional = userRepository.findById(idUsuario);
        return userOptional.isPresent();
    }

}
