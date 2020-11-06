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
@ActiveProfiles("test") 
class VeiculoControllerTest {

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
	public void deveListarVeiculos() throws Exception {
		URI uri = new URI ("/veiculos");
		
		mvc.perform(MockMvcRequestBuilders.get(uri).header("authorization", "Bearer " + gerarToken()))
		.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	

}
