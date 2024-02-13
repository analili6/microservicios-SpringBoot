package com.microservice.infante.controller;

import com.microservice.infante.dto.InfanteDto;
import com.microservice.infante.entity.Infante;
import com.microservice.infante.service.IInfanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/infante")
public class InfanteController {
    @Autowired
    private IInfanteService infanteService;
    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    @PostMapping("/crear")
    public String saveInfante(@RequestBody Infante infante){

        return infanteService.save(infante);
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    @PutMapping("/update/{curp}")
    public ResponseEntity<?> updateInfante(@PathVariable String curp, @RequestBody InfanteDto infanteDto) {
        infanteService.updateInfante(curp, infanteDto);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    @GetMapping("/all")
    public ResponseEntity<?> findAllInfante(){
        return ResponseEntity.ok(infanteService.findAll());
    }
    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    @GetMapping("/search/{curp}")
    public ResponseEntity<?> findByCurp(@PathVariable String curp){
        return  ResponseEntity.ok(infanteService.findByCurp(curp));
    }
    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    @GetMapping("/search-by-contacto/{curp}")
    public ResponseEntity<?> findContactoByInf(@PathVariable String curp){
        return ResponseEntity.ok(infanteService.findContactoByInf(curp));
    }
    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    @GetMapping("/search-by-tem/{curp}")
    public ResponseEntity<?> findTemByInf(@PathVariable String curp){
        return ResponseEntity.ok(infanteService.findTemByInf(curp));
    }
    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    @GetMapping("/search-by-fc/{curp}")
    public ResponseEntity<?> findFcByInf(@PathVariable String curp){
        return ResponseEntity.ok(infanteService.findFcByInf(curp));
    }
    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    @GetMapping("/search-by-fr/{curp}")
    public ResponseEntity<?> findFrByInf(@PathVariable String curp){
        return ResponseEntity.ok(infanteService.findFrByInf(curp));
    }
    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    @GetMapping("/search-by-user/{idUsuario}")
    public ResponseEntity<?> findByIdUsr(@PathVariable  Integer idUsuario){
        return  ResponseEntity.ok(infanteService.findByUsuario(idUsuario));
    }
    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    @GetMapping("/edad-en-meses/{curp}")
    public List<Integer> getEdadEnMeses(@PathVariable String curp) {
        return infanteService.findEdadEnMeses(curp);
    }

}
