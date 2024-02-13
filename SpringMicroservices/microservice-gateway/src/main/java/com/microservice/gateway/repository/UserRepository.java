package com.microservice.gateway.repository;

import com.microservice.gateway.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
    @Query("SELECT s FROM User s WHERE s.noSerie = :noSerie")
    List<User> findAllUserDis(String noSerie);
}
