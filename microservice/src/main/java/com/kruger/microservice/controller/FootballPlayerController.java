package com.kruger.microservice.controller;


import java.util.Optional;

import javax.print.attribute.standard.Media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kruger.microservice.model.FootBallPlayer;
import com.kruger.microservice.model.service.FootballPlayerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/footballplayer")
@Tag(name = "football-controller",description = "Add, delete and edit football players  ")
public class FootballPlayerController {
	
	@Autowired
	private FootballPlayerService service;
	
	@ApiResponse(description = "the list of players",responseCode = "200",content = @Content( array=@ArraySchema(schema = @Schema(implementation = FootBallPlayer.class))))
	@Operation(description = "get all footballers",summary = "calling this endpoint will give you all players")
	@Tag(name="All Footballers")
	@RequestMapping(method=RequestMethod.GET, produces="application/json" )
	public Iterable<FootBallPlayer> findAll(){
		return service.findAll();
	}
	
	@Operation(description = "add  footballers",summary = "calling this endpoint will allow you to add players by passing the player data as json in the request body")
	@Tag(name="Add Footballer")
	@PostMapping(value="/add",produces = MediaType.APPLICATION_JSON_VALUE)
	public FootBallPlayer save(@RequestBody FootBallPlayer entity) {
		return service.save(entity);
	}
	
	
	@Operation(description = "edit footballers",summary = "calling this endpoint will allow you to edit players")
	@Tag(name="Edit Footballer")
	@PostMapping(value="/edit/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public FootBallPlayer edit(@PathVariable Integer id, @RequestBody FootBallPlayer entity) {
		entity.setId(id);
		return service.save(entity);
	}
	
	@Operation(description = "delete footballers",summary = "calling this endpoint will allow you to delete players")
	@Tag(name="Delete Footballer")
	@PostMapping(value="/delete/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@PathVariable Integer id) {
		service.deleteById(id);
	}
	
	@ApiResponse(description = "the specific player",responseCode = "200",content = @Content(schema = @Schema(implementation = FootBallPlayer.class)))
	@Operation(description = "get specific footballer",summary = "calling this endpoint will allow you to fetch player by passing its id in the url as a path variable")
	@Tag(name="Find Footballer By ID")
	@GetMapping(value="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<FootBallPlayer> findById(@PathVariable Integer id){
		return service.findById(id);
	}
	
	
	@GetMapping(value = "/name/{name}")
	public String findByName (@PathVariable String name) {
		FootBallPlayer player=service.findByName(name);
		ObjectMapper mapper=new ObjectMapper();
		String json="";
		try{
			json=mapper.writeValueAsString(player);
			return json;	
		}catch(Exception e){

		}
		return player.toString();
	}
	

}
