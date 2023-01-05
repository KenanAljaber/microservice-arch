package com.kruger.microservice.model.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kruger.microservice.model.FootBallPlayer;
import com.kruger.microservice.model.repository.FootballPlayerRepository;

@Service
public class FootballPlayerService {
	
	@Autowired
	private FootballPlayerRepository repository;
	
	
	public FootBallPlayer findByName (String name) {
		return repository.findByName(name.toLowerCase()).orElseThrow(()-> new RuntimeException(String.format("There is no such a player with the name %s", name)));
	}
	
	public Iterable<FootBallPlayer> findAll(){
		return repository.findAll();
	}
	
	public FootBallPlayer save(FootBallPlayer entity) {
		return repository.save(entity);
		
	}
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}
	
	public Optional<FootBallPlayer> findById(Integer id){
		return repository.findById(id);
	}
	

}
