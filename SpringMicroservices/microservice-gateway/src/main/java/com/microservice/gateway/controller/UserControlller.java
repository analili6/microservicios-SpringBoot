package com.microservice.gateway.controller;

import com.microservice.gateway.dto.*;
import com.microservice.gateway.entity.User;
import com.microservice.gateway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserControlller {
    //Usamos @autowired para acceder a la clase de UserService y acceder a sus metodos
    @Autowired
    private UserService userService;
    //Definicion de los EndPoints para acceder a nuestra Api
    //@PostMapping sirve para enviar datos al servidor
    //@RequestBody extraer los datos  y mapear

    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        return new ResponseEntity<>(userService.register(registerDto), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    @PostMapping("/verify-account")
    public ResponseEntity<String> verifyAccount(@RequestBody VerifyAccountRequest verifyAccountRequest) {
        String email = verifyAccountRequest.getEmail();
        String otp = verifyAccountRequest.getOtp();
        return new ResponseEntity<>(userService.verifyAccount(email, otp), HttpStatus.OK);
    }

    //@RequestParam = Extrae parametros de la URL
    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    @PostMapping("/regenerate-otp")
    public ResponseEntity<String> regenerateOtp(@RequestBody RegenerateOtpRequest regenerateOtpRequest) {
        String email = regenerateOtpRequest.getEmail();
        return new ResponseEntity<>(userService.regenerateOtp(email), HttpStatus.OK);
    }
    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        return new ResponseEntity<>(userService.login(loginDto), HttpStatus.OK);
    }
    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPassword forgotPassword){
        return new ResponseEntity<>(userService.forgotPassword(forgotPassword), HttpStatus.OK);
    }
    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    @PostMapping ("/set-password")
    public ResponseEntity<String> setPassword(@RequestBody RecoverPassword recoverPassword){
        return  new ResponseEntity<>(userService.setPassword(recoverPassword), HttpStatus.OK);
    }
    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    @GetMapping("/search-by-noserie/{noSerie}")
    public ResponseEntity<?> findByNoSerie(@PathVariable String noSerie){
        return  ResponseEntity.ok(userService.findAllUserDis(noSerie));
    }
    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    @GetMapping("/search-by-user/{idUsuario}")
    public ResponseEntity<?> findUsrByInf(@PathVariable Integer idUsuario){
        return ResponseEntity.ok(userService. findUsrByInf(idUsuario));
    }

    //modificacion 16/12/2023
    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    @GetMapping("/search-by-email/{email}")
    public ResponseEntity<?> findIdByEmail(@PathVariable String email){
        return  ResponseEntity.ok(userService.findIdByEmail(email));
    }
    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    @GetMapping("/all")
    public ResponseEntity<?> findAllUser(){
        return ResponseEntity.ok(userService.findAll());
    }
//
//    @GetMapping("/search-user/{idUsuario}")
//    public ResponseEntity<?> findById(@PathVariable Integer idUsuario){
//        return  ResponseEntity.ok(userService.findById(idUsuario));
//    }
//



}
