package com.microservice.contacto.controller;

import com.microservice.contacto.dto.*;

import com.microservice.contacto.service.IContactoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contacto")
public class ContactoController {
//19006
    @Autowired
    private IContactoService contactoService;


    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        return new ResponseEntity<>(contactoService.register(registerDto), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    @PostMapping("/verify-account")
    public ResponseEntity<String> verifyAccount(@RequestBody VerifyAccountRequest verifyAccountRequest) {
        String email = verifyAccountRequest.getEmail();
        String otp = verifyAccountRequest.getOtp();
        return new ResponseEntity<>(contactoService.verifyAccount(email, otp), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    @PostMapping("/regenerate-otp")
    public ResponseEntity<String> regenerateOtp(@RequestBody RegenerateOtpRequest regenerateOtpRequest) {
        String email = regenerateOtpRequest.getEmail();
        return new ResponseEntity<>(contactoService.regenerateOtp(email), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    @PutMapping("/update/{idContacto}")
    public ResponseEntity<String> update(@PathVariable Integer idContacto, @RequestBody UpdateDto updateDto) {
        return new ResponseEntity<>(contactoService.update(idContacto, updateDto), HttpStatus.OK);
    }
    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    @DeleteMapping("/delete/{idContacto}")
    public  ResponseEntity<?> deleteById(@PathVariable Integer idContacto){

        if(idContacto != null){
            contactoService.deleteById(idContacto);
            return ResponseEntity.ok("Contacto inabilitado , ya no recibira alertas ");
        }
        return ResponseEntity.badRequest().build();
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    @PutMapping("/enable-contact/{idContacto}")
    public  ResponseEntity<?> enableById(@PathVariable Integer idContacto){

        if(idContacto != null){
            contactoService.enableContact(idContacto);
            return ResponseEntity.ok("Contacto habilitado, recibira alertas ");
        }
        return ResponseEntity.badRequest().build();
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    @GetMapping("/all")
    public ResponseEntity<?> findAllContacto(){
        return ResponseEntity.ok(contactoService.findAll());
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    @GetMapping("/search/{idContacto}")
    public ResponseEntity<?> findById(@PathVariable Integer idContacto){
        return  ResponseEntity.ok(contactoService.findById(idContacto));
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    @GetMapping("/search-by-curp/{curp}")
    public ResponseEntity<?> findByCurp(@PathVariable  String curp){
        return  ResponseEntity.ok(contactoService.findByCurp(curp));
    }

    @GetMapping("/search-contact-by-curp/{curp}")
    public  ResponseEntity<?> findEmailByCurp(@PathVariable  String curp){
        return  ResponseEntity.ok(contactoService.findActiveEmailByCurp(curp));

    }



}
