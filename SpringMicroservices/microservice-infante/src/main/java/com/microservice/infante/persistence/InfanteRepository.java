package com.microservice.infante.persistence;

import com.microservice.infante.entity.Infante;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface InfanteRepository extends CrudRepository<Infante, String> {


    @Query("SELECT i FROM Infante i WHERE i.idUsuario = :idUsuario")
    List<Infante> findAllInfUsr(Integer idUsuario);

    @Query("SELECT i FROM Infante i WHERE i.idUsuario = :idUsuario")
    List<Infante> findByIdUsuario(Integer idUsuario);

    @Query("SELECT t.fechanac FROM Infante t WHERE t.curp = :curp ")
    List<LocalDate> findEdad(@Param("curp") String curp);



}
