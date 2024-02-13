package com.microservice.dispositivo.controller;

import com.microservice.dispositivo.entity.Dispositivo;
import com.microservice.dispositivo.service.IDispositivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dispositivo")
public class DispositivoController {
    @Autowired
    private IDispositivoService dispositivoService;

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveDispositivo(@RequestBody Dispositivo dispositivo){
        dispositivoService.save(dispositivo);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAllDispositivo(){
        return ResponseEntity.ok(dispositivoService.findAll());
    }

    @GetMapping("/search/{noSerie}")
    public ResponseEntity<?> findByNoSerie(@PathVariable String noSerie){
        return  ResponseEntity.ok(dispositivoService.findDisByUsr(noSerie));
    }

    @GetMapping("/search-by-dispositivo/{noSerie}")
    public ResponseEntity<?> findDisByUsr(@PathVariable String noSerie){
        return ResponseEntity.ok(dispositivoService.findDisByUsr(noSerie));
    }




}
