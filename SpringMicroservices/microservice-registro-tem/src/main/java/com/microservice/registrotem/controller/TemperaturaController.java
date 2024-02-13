package com.microservice.registrotem.controller;

import com.microservice.registrotem.entity.Temperatura;
import com.microservice.registrotem.http.response.InfanteTemResponse;
import com.microservice.registrotem.service.ITemperaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/temperatura")
public class TemperaturaController {

    @Autowired
    private ITemperaturaService temperaturaService;
    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    @PostMapping("/create")
    public String saveTemperatura(@RequestBody Temperatura temperatura) {
       return temperaturaService.save(temperatura);
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    @GetMapping("/all")
    public ResponseEntity<?> findAllTemperatura(){
        return ResponseEntity.ok(temperaturaService.findAll());
    }
    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    @GetMapping("/search/{id_RegistroTem}")
    public ResponseEntity<?> findById(@PathVariable Integer id_RegistroTem) {
        return  ResponseEntity.ok(temperaturaService.findById(id_RegistroTem));
    }
    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    @GetMapping("/search-by-curp/{curp}")
    public ResponseEntity<?> findByCurp(@PathVariable  String curp){
        return  ResponseEntity.ok(temperaturaService.findByCurp(curp));
    }
    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    @GetMapping("/ultima/{curp}")
    public ResponseEntity<List<Temperatura>> obtenerUltimaTemPorCurp(@PathVariable String curp) {
        List<Temperatura> ultimaTemperatura = temperaturaService.findLastTempByCurp(curp);

        if (ultimaTemperatura.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(ultimaTemperatura);
        }
    }
    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    @GetMapping("/email-contact-tem/{curp}")
    public ResponseEntity<?> findEmailContact(@PathVariable String curp){
        return ResponseEntity.ok(temperaturaService.findEmailContacto(curp));
    }
    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    @GetMapping("/edad-infante/{curp}")
    public ResponseEntity<InfanteTemResponse> findEdadByInfante(@PathVariable String curp) {
        return ResponseEntity.ok(temperaturaService.findEdadByInfante(curp));
    }



}
