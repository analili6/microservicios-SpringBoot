package com.microservice.infante.service;

import com.microservice.infante.client.ContactoInfClient;
import com.microservice.infante.client.FrecuenciaCInfClient;
import com.microservice.infante.client.FrecuenciaRInfClient;
import com.microservice.infante.client.TemperaturaInfClient;
import com.microservice.infante.dto.*;
import com.microservice.infante.entity.Infante;
import com.microservice.infante.http.response.ContactoByInfResponse;
import com.microservice.infante.http.response.FrecCByInfResponse;
import com.microservice.infante.http.response.FrecRByInfResponse;
import com.microservice.infante.http.response.TemperaturaByInfResponse;
import com.microservice.infante.persistence.InfanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InfanteServiceImpl implements  IInfanteService{
    @Autowired
    private InfanteRepository infanteRepository;
    @Autowired
    private TemperaturaInfClient temperaturaInfClient;
    @Autowired
    private ContactoInfClient contactoInfClient;
    @Autowired
    private FrecuenciaCInfClient frecuenciaCInfClient;

    @Autowired
    private FrecuenciaRInfClient frecuenciaRInfClient;


    @Override
    public List<Infante> findAll() {
        return (List<Infante>) infanteRepository.findAll();
    }

    @Override
    public Infante findByCurp(String curp) {
        return infanteRepository.findById(curp).orElseThrow();
    }

    @Override
    public String save(Infante infante) {
        Optional<Infante> infanteExistente = infanteRepository.findById(infante.getCurp());

        if (infanteExistente.isPresent()) {
            return "La CURP ya está registrada en la base de datos";
        } else {
            infanteRepository.save(infante);
            return "Infante registrado con éxito";
        }
    }
    @Override
    public ContactoByInfResponse findContactoByInf(String curp) {
        //Consultar el registro de frecuencia res
        Infante infante = infanteRepository.findById(curp).orElse(new Infante());
        //Obtener historial
        //Consultar el msvc de historial
        List<ContactoInfDTO> contactoInfDTOList = contactoInfClient.findAllContactoByCurp(curp);
        return ContactoByInfResponse.builder()
                .nombreInfante(infante.getNombreInfante())
                .contactoInfDTOList(contactoInfDTOList)
                .build();
    }

    @Override
    public void updateInfante(String curp, InfanteDto infanteDto) {
        Optional<Infante> infanteOptional = Optional.ofNullable(findByCurp(curp));
        if (infanteOptional.isPresent()) {
            Infante infante = infanteOptional.get();
            // Actualizar los campos del infante
            infante.setNombreInfante(infanteDto.getNombreInfante());
            infante.setApPaterno(infanteDto.getApPaterno());
            infante.setApMaterno(infanteDto.getApMaterno());
            infante.setSexo(infanteDto.getSexo());
            infante.setTipoSangre(infanteDto.getTipoSangre());
            infante.setFechanac(infanteDto.getFechanac());
            infante.setTalla(infanteDto.getTalla());
            infante.setPeso(infanteDto.getPeso());
            infante.setObservaciones(infanteDto.getObservaciones());
            // Guardar los cambios en el repositorio
            infanteRepository.save(infante);
        }
    }

    @Override
    public TemperaturaByInfResponse findTemByInf(String curp) {
        Infante infante = infanteRepository.findById(curp).orElse(new Infante());
        List<TemperaturaInfDTO> temperaturaInfDTOList = temperaturaInfClient.findAllTemByCurp(curp);
        return TemperaturaByInfResponse.builder()
                .nombreInfante(infante.getNombreInfante())
                .fechanac(infante.getFechanac())
                .temperaturaInfDTOList(temperaturaInfDTOList)
                .build();
    }

    @Override
    public List<Integer> findEdadEnMeses(String curp) {
        List<LocalDate> fechasNacimiento = infanteRepository.findEdad(curp);

        // Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();

        // Calcular la edad en meses para cada fecha de nacimiento
        List<Integer> edadesEnMeses = fechasNacimiento.stream()
                .map(fechaNacimiento -> calcularEdadEnMeses(fechaNacimiento, fechaActual))
                .collect(Collectors.toList());
        return edadesEnMeses;
    }

    private int calcularEdadEnMeses(LocalDate fechaNacimiento, LocalDate fechaActual) {
        Period periodo = Period.between(fechaNacimiento, fechaActual);
        return (int) periodo.toTotalMonths();
    }

    @Override
    public FrecCByInfResponse findFcByInf(String curp) {
        Infante infante = infanteRepository.findById(curp).orElse(new Infante());
        List<FrecuenciaCDTO> frecuenciaCDTOList = frecuenciaCInfClient.findAllFcByCurp(curp);
        return FrecCByInfResponse.builder()
                .nombreInfante(infante.getNombreInfante())
                .frecuenciaCDTOList(frecuenciaCDTOList)
                .build();
    }

    @Override
    public FrecRByInfResponse findFrByInf(String curp) {
        Infante infante = infanteRepository.findById(curp).orElse(new Infante());
        List<FrecuenciaRDTO> frecuenciaRDTOList = frecuenciaRInfClient.findAllFrByCurp(curp);
        return FrecRByInfResponse.builder()
                .nombreInfante(infante.getNombreInfante())
                .frecuenciaRDTOList(frecuenciaRDTOList)
                .build();
    }

    @Override
    public List<Infante> findByUsuario(Integer idUsuario) {
        return infanteRepository.findAllInfUsr(idUsuario);
    }

    @Override
    public List<Infante> findByIdUsuario(Integer idUsuario) {
        return infanteRepository.findByIdUsuario(idUsuario);
    }

//    @Override
//    public List<Infante> findEdadInfante(String curp) {
//        return infanteRepository.findEdad(curp);
//    }

}



