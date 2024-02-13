package com.microservice.registrofr.controller;

import com.microservice.registrofr.dto.ContactoTemDTO;
import com.microservice.registrofr.entity.FrecuenciaR;
import com.microservice.registrofr.http.response.ContactoInfResponse;
import com.microservice.registrofr.http.response.InfanteFrResponse;
import com.microservice.registrofr.service.IFrecuenciaRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/frecuenciar")
public class FrecuenciaRController {
    @Autowired
    private IFrecuenciaRService frecuenciaRService;
    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    @PostMapping("/create")
    public String saveFrecuenciaR(@RequestBody FrecuenciaR frecuenciaR) {
         return frecuenciaRService.save(frecuenciaR);
    }
    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    @GetMapping("/all")
    public ResponseEntity<?> findAllFrecuenciaR(){
        return ResponseEntity.ok(frecuenciaRService.findAll());
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    @GetMapping("/search/{id_RegistroFr}")
    public ResponseEntity<?> findById(@PathVariable Integer id_RegistroFr) {
        return  ResponseEntity.ok(frecuenciaRService.findById(id_RegistroFr));
    }
    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    @GetMapping("/search-by-curp/{curp}")
    public ResponseEntity<?> findByCurp(@PathVariable  String curp){
        return  ResponseEntity.ok(frecuenciaRService.findByCurp(curp));
    }
    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    @GetMapping("/ultima/{curp}")
    public ResponseEntity<List<FrecuenciaR>> obtenerUltimaTemPorCurp(@PathVariable String curp) {
        List<FrecuenciaR> ultimaFr = frecuenciaRService.findLastFrByCurp(curp);

        if (ultimaFr.isEmpty()) {
            return ResponseEntity.notFound().build(
            );
        } else {
            return ResponseEntity.ok(ultimaFr);
        }
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    @GetMapping("/email-contact/{curp}")
    public ResponseEntity<?> findEmailContact(@PathVariable String curp){
        return ResponseEntity.ok(frecuenciaRService.findEmailContacto(curp));
    }
    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    @GetMapping("/edad-infante/{curp}")
    public ResponseEntity<InfanteFrResponse> findEdadByInfante(@PathVariable String curp) {
        return ResponseEntity.ok(frecuenciaRService.findEdadByInfante(curp));
    }

}
