package com.microservice.dispositivo.service;

import com.microservice.dispositivo.client.UsuarioDisClient;
import com.microservice.dispositivo.dto.UsuarioDisDTO;
import com.microservice.dispositivo.entity.Dispositivo;
import com.microservice.dispositivo.http.response.UsuarioByDisResponse;
import com.microservice.dispositivo.persistence.DispositivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DispositivoServiceImpl implements IDispositivoService{
    @Autowired 
    private DispositivoRepository dispositivoRepository;
    @Autowired
    private UsuarioDisClient usuarioDisClient;
    @Override
    public List<Dispositivo> findAll() {
        return (List<Dispositivo>) dispositivoRepository.findAll();

    }

    @Override
    public Dispositivo findById(String noSerie) {
        return dispositivoRepository.findById(noSerie).orElseThrow();

    }

//    @Override
//    public Dispositivo findBySerie(String noSerie) {
//        return dispositivoRepository.findById(noSerie).orElseThrow();
//    }

    @Override
    public void save(Dispositivo dispositivo)  {
        dispositivoRepository.save(dispositivo);
    }

    @Override
    public UsuarioByDisResponse findDisByUsr(String noSerie) {
        Dispositivo dispositivo = dispositivoRepository.findById(noSerie).orElse(new Dispositivo());
        List<UsuarioDisDTO> usuarioDisDTOList = usuarioDisClient.findAllUsuarioByNoSerie(noSerie);

        return UsuarioByDisResponse.builder()
                .nombreUsuario(dispositivo.getNombre())
                .usuarioDisDTOList(usuarioDisDTOList)
                .build();
    }
}
