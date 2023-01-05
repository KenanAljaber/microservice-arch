package com.kruger.microservice;




import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigInteger;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.jayway.jsonpath.JsonPath;
import com.kruger.microservice.model.FootBallPlayer;


import net.minidev.json.JSONArray;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = MicroserviceApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestFootballPlayerController {
	
	
	private RestTemplate rt;
	private HttpHeaders headers;
	private static int totalPlayers;
	
	@LocalServerPort
	private int port;
	
	
	@BeforeEach
	public void setUp () {
		System.out.println("intitlizing");
		rt=new RestTemplate();
		headers=new HttpHeaders();
	}
	
	private String generateUrl(String endPoint) {
		return "http://localhost:"+port+endPoint;
	}
	
	@Test
	@Order(1)
	public void findAll_getAllPlayers_listOfPLayers() {
		System.out.println("Finding all players**********************************");
		
		HttpEntity<String> entity= new HttpEntity<>(null,headers);
		ResponseEntity<String> response=rt.exchange(generateUrl("/footballplayer"), HttpMethod.GET,
												entity,String.class);
		//testing response status
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		//testing response data size(players list size)
		JSONArray jsonArray=JsonPath.read(response.getBody(),"$.[*]");
		HashMap<String,Object> map=(HashMap<String, Object>) jsonArray.get(jsonArray.size()-1);
		totalPlayers=(int)map.get("id");
		System.out.println("---------------------------------------------------"+totalPlayers);
		assertThat(jsonArray).isNotNull();
		assertThat(jsonArray.size()).isGreaterThan(0);
	
		
	}
	

	@Test
	@Order(2)
	public void findPlayerById_specificPlayer_onePlayer() {
		System.out.println("Finding player**********************************");
	ResponseEntity<FootBallPlayer> response=rt.exchange(generateUrl("/footballplayer/1"),HttpMethod.GET
			,null,FootBallPlayer.class);
	
	//testing response status
	assertThat(HttpStatus.OK).isEqualTo(response.getStatusCode());
	System.out.println(response.getBody().getName());
	assertThat(response.getBody()).isNotNull();
	assertThat(response.getBody().getName()).isEqualTo("Manuel");
	}
	
	

	@Test
	@Order(3)
	public void savePlayer_addingNewPlayer() {
		
		//getting the tootal number of players before adding 
		System.out.println("Saving player**********************************");
		HttpEntity<FootBallPlayer> entity=new HttpEntity<FootBallPlayer>(new FootBallPlayer("demo","player",100,"demo team","NA",BigInteger.valueOf(500))
				,headers);
		ResponseEntity<FootBallPlayer> resp=rt.exchange(generateUrl("/footballplayer/add"), HttpMethod.POST, entity, FootBallPlayer.class);
		
		//testing the response status
		assertThat(HttpStatus.OK).isEqualTo(resp.getStatusCode());
		//testing if we get a valid footballplayer as a body for the response
		assertThat(resp.getBody()).isNotNull();
	
		
	}
	

	@Test
	@Order(4)
	public void deletePlayer() {
		System.out.println("Deleting player**********************************");
		//the id of the player we will delete is the final player
		int id=totalPlayers;
		ResponseEntity<String> resp=rt.exchange(generateUrl("/footballplayer/delete/"+id), HttpMethod.POST, null, String.class);
		findAll_getAllPlayers_listOfPLayers();
		//testing the response code
		assertThat(HttpStatus.OK).isEqualTo(resp.getStatusCode());
		
		
		
	}
	

	
	}


