package com.everis.estacionamento.controller;

import java.net.URI;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
import com.everis.estacionamento.controller.dto.EstacionamentoDtoParaReceber;
import com.everis.estacionamento.model.Estacionamento;
import com.everis.estacionamento.service.EstacionamentoService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class EstacionamentoControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private TokenService tokenService;

	@MockBean
	private EstacionamentoService estacionamentoService;

	@Before
	public String gerarToken() {

		UsernamePasswordAuthenticationToken autenticar = new UsernamePasswordAuthenticationToken(
				"gabrielamartinez@gmail.com", "123456");
		Authentication authentication = authManager.authenticate(autenticar);
		String token = tokenService.gerarToken(authentication);
		return token;
	}

	@Test
	public void testaSalvarEstacionamento() throws Exception {
		URI uri = new URI("/estacionamento");
		EstacionamentoDtoParaReceber estacionamentoDto = new EstacionamentoDtoParaReceber(1, 10);
		Mockito.when(estacionamentoService.save(estacionamentoDto)).thenReturn(new Estacionamento(1, 10));
		String json = "{\"valorTarifa\":\"1\", \"totalVagasEstacionamento\":\"10\"}";
		mvc.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON)
				.header("authorization", "Bearer " + gerarToken())).andExpect(MockMvcResultMatchers.status().is(200));
	}

	@Test
	public void naoDeveRemoverEstacionamentoPoisPossuiTickets() throws Exception {
		URI uri = new URI("/veiculos/75");
		mvc.perform(MockMvcRequestBuilders.delete(uri).header("authorization", "Bearer " + gerarToken()))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void deveAtualizarEstacionamento() throws Exception {
		URI uri = new URI("/estacionamento/1");
		String json = "{\"valorTarifa\":\"5\", \"totalVagasEstacionamento\":\"10\"}";
		mvc.perform(MockMvcRequestBuilders.put(uri).content(json).contentType(MediaType.APPLICATION_JSON)
				.header("authorization", "Bearer " + gerarToken())).andExpect(MockMvcResultMatchers.status().is(200));
	}

}
