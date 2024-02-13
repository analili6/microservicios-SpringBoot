package com.microservice.registrofc.persistence;

import com.microservice.registrofc.entity.FrecuenciaC;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public  interface FrecuenciaCRepository extends CrudRepository<FrecuenciaC, Integer> {

    @Query("SELECT x FROM FrecuenciaC x WHERE x.curp = :curp")
    List<FrecuenciaC> findAllFcInf(String curp);

    @Query("SELECT t FROM FrecuenciaC t WHERE t.curp = :curp ORDER BY t.fecha_hora DESC LIMIT 1")
    List<FrecuenciaC> findLastFcByCurp(@Param("curp") String curp);

}
