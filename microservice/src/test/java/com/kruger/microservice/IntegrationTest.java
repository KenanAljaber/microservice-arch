package com.kruger.microservice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.kruger.microservice.model.FootBallPlayer;
import com.kruger.microservice.model.repository.FootballPlayerRepository;
import com.kruger.microservice.model.service.FootballPlayerService;

@SpringBootTest(classes = {IntegrationTest.class})
public class IntegrationTest {
	
	@Mock
	private FootballPlayerRepository repo;
	
	@InjectMocks
	private FootballPlayerService service;
	
	private List<FootBallPlayer> players;
	
	
	@BeforeEach
	public void initilize () {
		players=new ArrayList<>();
		players.add(new FootBallPlayer("demo","demo",500,"demo team","position",BigInteger.valueOf(500)));
		players.add(new FootBallPlayer("demo","demo",500,"demo team","position",BigInteger.valueOf(500)));
		players.add(new FootBallPlayer("demo","demo",500,"demo team","position",BigInteger.valueOf(500)));
		players.add(new FootBallPlayer("demo","demo",500,"demo team","position",BigInteger.valueOf(500)));
		players.add(new FootBallPlayer("demo","demo",500,"demo team","position",BigInteger.valueOf(500)));
		players.add(new FootBallPlayer("demo","demo",500,"demo team","position",BigInteger.valueOf(500)));
		players.add(new FootBallPlayer("demo","demo",500,"demo team","position",BigInteger.valueOf(500)));
		players.add(new FootBallPlayer("demo","demo",500,"demo team","position",BigInteger.valueOf(500)));
		players.add(new FootBallPlayer("demo","demo",500,"demo team","position",BigInteger.valueOf(500)));
		
	}
	
	@Test
	public void test_findAll() {
		when(repo.findAll()).thenReturn(players);
		List<FootBallPlayer> value=(List<FootBallPlayer>) service.findAll();
		assertThat(value.size()).isEqualTo(players.size());
		
	}
	
	@Test
	public void test_save() {
		FootBallPlayer newPlayer=new FootBallPlayer("new","new",500,"demo team","position",BigInteger.valueOf(500));
		when(repo.save(newPlayer)).thenReturn(newPlayer);
		assertEquals(newPlayer,service.save(newPlayer));
	}
	
	@Test
	public void test_DeleteById() {
		FootBallPlayer playerToDelete=new FootBallPlayer(9,"demo","demo",500,"demo team","position",BigInteger.valueOf(500));
		service.deleteById(playerToDelete.getId());
		verify(repo, times(1)).deleteById(playerToDelete.getId());;
	}

}
