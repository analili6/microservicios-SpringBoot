package com.microservice.registrotem.persistence;

import com.microservice.registrotem.entity.Temperatura;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemperaturaRepository extends CrudRepository<Temperatura, Integer> {

    @Query("SELECT t FROM Temperatura t WHERE t.curp = :curp")
    List<Temperatura> findAllTemInf(String curp);

    @Query("SELECT t FROM Temperatura t WHERE t.curp = :curp ORDER BY t.fecha_hora DESC LIMIT 1")
    List<Temperatura> findLastTemperatureByCurp(@Param("curp") String curp);


//    @Query("SELECT t FROM Temperatura t WHERE t.curp = :curp ")
//    List<Temperatura> findEdad(@Param("curp") String curp);

}

