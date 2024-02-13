package com.microservice.contacto.persistence;

import com.microservice.contacto.entity.Contacto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactoRepository extends CrudRepository<Contacto, Integer> {
    Optional<Contacto> findByEmail(String email);

    boolean existsByEmail(String email);


    @Query("SELECT c FROM Contacto c WHERE c.curp = :curp")
    List<Contacto> findAllContactoInf(String curp);
    @Query("SELECT c.email FROM Contacto c WHERE c.curp = :curp AND c.active = true")
    List<String> findActiveEmailByCurp(@Param("curp") String curp);



   // SELECT email FROM contacto WHERE curp = 'AESA001223MMCRLNA2'



}
