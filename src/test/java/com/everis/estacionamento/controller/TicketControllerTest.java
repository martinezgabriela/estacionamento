package com.everis.estacionamento.controller;

import java.net.URI;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.everis.estacionamento.configuracao.security.TokenService;

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
	public void deveCadastrarTicket() throws Exception {
		URI uri = new URI("/tickets");
		String json = "{\"idVeiculo\":\"87\",\"idEstacionamento\":\"1\"}";
		mvc.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON)
				.header("authorization", "Bearer " + gerarToken())).andExpect(MockMvcResultMatchers.status().is(201));

	}

	@Test
	public void deveRegistrarSaida() throws Exception {
		URI uri = new URI("/tickets/registrarsaida/37");
		mvc.perform(MockMvcRequestBuilders.put(uri).header("authorization", "Bearer " + gerarToken()))
				.andExpect(MockMvcResultMatchers.status().is(200));

	}
	
	@Test
	public void deveListarTickets() throws Exception {
		URI uri = new URI ("/tickets");
		mvc.perform(MockMvcRequestBuilders.get(uri)
		.header("authorization", "Bearer " + gerarToken()))
		.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test
	public void deveListarTicketsPelaPlaca() throws Exception {
		URI uri = new URI ("/tickets/?placa=MGM9090");
		mvc.perform(MockMvcRequestBuilders.get(uri)
		.header("authorization", "Bearer " + gerarToken()))
		.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test
	public void deveRemoverTicket() throws Exception {
		URI uri = new URI ("/tickets/62");
		mvc.perform(MockMvcRequestBuilders.delete(uri)
		.header("authorization", "Bearer " + gerarToken()))
		.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test
	public void deveTentarRemoverTicketPoremDarExcecaoPoisIdInvalido() throws Exception {
		URI uri = new URI ("/tickets/660");
		mvc.perform(MockMvcRequestBuilders.delete(uri)
		.header("authorization", "Bearer " + gerarToken()))
		.andExpect(MockMvcResultMatchers.status().is(400));
	}
	

}
