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
//@WebMvcTest(Controller.class)
@ActiveProfiles("test") 
public class ClienteControllerTest {
	
	@Autowired
	private MockMvc mvc;

	@Test
	public void deveCadastrarCliente() throws Exception {
		URI uri = new URI ("/clientes");
		String json = "{\"nome\":\"gabriela\", \"telefone\":\"99999999\", \"email\":\"gabriela@martinez.com\"}";
		mvc.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is(201));
	}

}

