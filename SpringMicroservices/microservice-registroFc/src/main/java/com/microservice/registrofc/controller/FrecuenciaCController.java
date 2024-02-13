package com.microservice.registrofc.controller;

import com.microservice.registrofc.entity.FrecuenciaC;
import com.microservice.registrofc.http.response.InfanteFcResponse;
import com.microservice.registrofc.service.IFrecuenciaCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/frecuenciac")
public class FrecuenciaCController {

    @Autowired
    private IFrecuenciaCService frecuenciaCService;
    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public String saveFrecuenciaC(@RequestBody FrecuenciaC frecuenciaC) {
       return frecuenciaCService.save(frecuenciaC);
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    @GetMapping("/all")
    public ResponseEntity<?> findAllFrecuenciaC(){
        return ResponseEntity.ok(frecuenciaCService.findAll());
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    @GetMapping("/search/{id_RegistroFc}")
    public ResponseEntity<?> findById(@PathVariable Integer id_RegistroFc) {
        return  ResponseEntity.ok(frecuenciaCService.findById(id_RegistroFc));
    }
    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    @GetMapping("/search-by-curp/{curp}")
    public ResponseEntity<?> findByCurp(@PathVariable  String curp){
        return  ResponseEntity.ok(frecuenciaCService.findByCurp(curp));
    }
    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    @GetMapping("/ultima/{curp}")
    public ResponseEntity<List<FrecuenciaC>> obtenerUltimaFcPorCurp(@PathVariable String curp) {
        List<FrecuenciaC> ultimaFc = frecuenciaCService.findLastFcByCurp(curp);

        if (ultimaFc.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {

            return ResponseEntity.ok(ultimaFc);
        }
    }
    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    @GetMapping("/email-contact-fc/{curp}")
    public ResponseEntity<?> findEmailContact(@PathVariable String curp){
        return ResponseEntity.ok(frecuenciaCService.findEmailContacto(curp));
    }
    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    @GetMapping("/edad-infante/{curp}")
    public ResponseEntity<InfanteFcResponse> findEdadByInfante(@PathVariable String curp) {
        return ResponseEntity.ok(frecuenciaCService.findEdadByInfante(curp));
    }

}
