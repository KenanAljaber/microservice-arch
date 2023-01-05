package com.kruger.microservice.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.kruger.microservice.model.FootBallPlayer;

public interface FootballPlayerRepository extends CrudRepository<FootBallPlayer, Integer> {

	//metodos genericos trabajan a partir de la persistencia y utilizamos para el CRUD guardar, eliminar y buscar sobre entities
	
	@Query("from FootBallPlayer p where lower(p.name)= :name")
	Optional<FootBallPlayer> findByName(@Param("name") String name);
	
}
