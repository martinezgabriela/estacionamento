package com.everis.estacionamento.service.impl;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.everis.estacionamento.model.Cliente;
import com.everis.estacionamento.model.Estacionamento;
import com.everis.estacionamento.repository.ClienteRepository;
import com.everis.estacionamento.service.ClienteService;




@RunWith(SpringRunner.class)
@SpringBootTest
public class ClienteServiceImplTest {

	@TestConfiguration
	static class ClienteServiceImplTestConfiguration{
		
		@Bean
		public ClienteService clienteService(){
			return new ClienteServiceImpl();
		}
		
	}
	
	@Autowired
	private ClienteService clienteService;
	
	@MockBean
	private ClienteRepository clienteRepository;
	

	
	@Test
	public void testandoFindAll() {		
		Mockito.when(clienteRepository.findAll()).thenReturn(Stream.of(new Cliente ("Gabriela", "998189815", "gabriela@gmail.com"),
				new Cliente ("Ana", "9999999", "ana@gmail.com"), 
				new Cliente ("Anderson", "7777777", "anderson@gmail.com")).collect(Collectors.toList()));
		Assertions.assertThat(clienteService.findAll().size()).isEqualTo(3);	
	}
	
	
	@Test
	public void testandoFindByNome() {
		String nome = "Gabriela";
		Mockito.when(clienteRepository.findByNome(nome)).thenReturn(Stream.of(new Cliente ("Gabriela",
				"998189815", "gabriela@gmail.com")).collect(Collectors.toList()));
		Assertions.assertThat(clienteService.findByNome(nome).size()).isEqualTo(1);	
	}
	
	@Test
	public void testafindById() {
		Long id = 1L;
		Cliente cliente = new Cliente ("Ana", "9999999", "ana@gmail.com");
		Mockito.when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));
		Assertions.assertThat(cliente.getId()).isEqualTo((clienteRepository.findById(id)).get().getId());
		
	}
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
