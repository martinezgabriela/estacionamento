package com.everis.estacionamento.controller;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test") 
class VeiculoControllerTest {

	@Autowired
	private MockMvc mvc;
	
	
	@Test
	public void deveListarVeiculos() throws Exception {
		URI uri = new URI ("/veiculos");
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJBUEkgZG8gRXN0YWNpb25hbWVudG8iLCJzdWIiOiI2IiwiaWF0IjoxNjA0NjY2MzAxLCJleHAiOjE2MDQ3NTI3MDF9.A8LFRNbWMjFDM6fI6DKqkStJPxb6AtHN4vffzrq1ieI";
		mvc.perform(MockMvcRequestBuilders.get(uri).header("authorization", "Bearer " + token))
		.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	

}
