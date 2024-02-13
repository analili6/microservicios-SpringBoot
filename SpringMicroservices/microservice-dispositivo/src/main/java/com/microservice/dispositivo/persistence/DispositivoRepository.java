package com.microservice.dispositivo.persistence;

import com.microservice.dispositivo.entity.Dispositivo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DispositivoRepository extends CrudRepository<Dispositivo, String> {

}
