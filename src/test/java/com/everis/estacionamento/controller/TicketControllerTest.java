package com.everis.estacionamento.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.everis.estacionamento.configuracao.security.TokenService;
import com.everis.estacionamento.model.Ticket;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
//@WebMvcTest(Controller.class)
@ActiveProfiles("test") 
class TicketControllerTest {
	
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	private TokenService tokenService;
	
	@Before
	public String gerarToken() {

		UsernamePasswordAuthenticationToken autenticar = new UsernamePasswordAuthenticationToken(
				"gabrielamartinez@gmail.com", "123456");
		Authentication authentication = authManager.authenticate(autenticar);
		String token = tokenService.gerarToken(authentication);
		return token;
	}
	
	
	@Test
	public void deveCadastrarTicket() throws Exception{
		URI uri = new URI ("/tickets");
		String json = "{\"idVeiculo\":\"2\",\"idEstacionamento\":\"1\"}";
				mvc.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON)
						.header("authorization", "Bearer " + gerarToken()))
				.andExpect(MockMvcResultMatchers.status().is(201));
		
	}
	
	@Test
	public void deveRegistrarSaida() throws Exception{
		URI uri = new URI ("/tickets/registrarsaida/37");
		String json = "{\"idVeiculo\":\"2\",\"idEstacionamento\":\"1\"}";
				mvc.perform(MockMvcRequestBuilders.put(uri)
						.header("authorization", "Bearer " + gerarToken()))
				.andExpect(MockMvcResultMatchers.status().is(200));
		
	}
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}


