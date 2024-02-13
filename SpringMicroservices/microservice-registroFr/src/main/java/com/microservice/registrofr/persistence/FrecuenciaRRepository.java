package com.microservice.registrofr.persistence;

import com.microservice.registrofr.entity.FrecuenciaR;
import feign.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FrecuenciaRRepository extends CrudRepository<FrecuenciaR, Integer> {
    @Query("SELECT r FROM FrecuenciaR r WHERE r.curp = :curp")
    List<FrecuenciaR> findAllFrInf(String curp);

    @Query("SELECT t FROM FrecuenciaR t WHERE t.curp = :curp ORDER BY t.fecha_hora DESC LIMIT 1")
    List<FrecuenciaR> findLastFrByCurp(@Param("curp") String curp);
}
