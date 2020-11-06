package com.everis.estacionamento.service.impl;


import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.everis.estacionamento.model.Cliente;
import com.everis.estacionamento.repository.ClienteRepository;
import com.everis.estacionamento.service.ClienteService;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test") 
class ClienteServiceImplTest {

	@TestConfiguration
	static class ClienteServiceImplTestContextConfiguration{
		
		@Bean
		public ClienteService clienteService() {
			return new ClienteServiceImpl();
		}
	}
	
	@Autowired
	private ClienteService clienteService;
	
//	//cria um Mock do ClienteRepository
//	@MockBean
//	private ClienteRepository clienteRepository;
	
//	@Before
//	public void setUp() {
//		Cliente cliente = new Cliente("Gabriela", "998189815", "gabrielamartinez.19@gmail.com"); 
//		Mockito.when(clienteRepository.findByNome(cliente.getNome())).thenReturn(cliente);
//	}
	
	
//	@Test
//	public void devePersistirCliente() {
//		Cliente cliente = new Cliente("Gabriela", "998189815", "gabrielamartinez.19@gmail.com"); 
//		clienteService.save(cliente);
//		Assertions.assertThat(clienteService.findByNome("Gabriela").getEmail()).isNotEqualTo("gabriela19@gmail.com");
//	}
//	
//	@Test
//	public void deveAtualizarEPersistirCliente() {
//		Cliente cliente = new Cliente("Gabriela", "998189815", "gabrielamartinez.19@gmail.com"); 
//		Cliente clienteSalvo = clienteService.save(cliente);
//		Cliente clienteDadosAtualizar = new Cliente("Gabriella", "998189816", "gabriellamartinez.19@gmail.com"); 
//		clienteService.atualizar(clienteSalvo.getId(), clienteDadosAtualizar);
//		Assertions.assertThat(clienteService.findByNome("Gabriella").getEmail()).isNotEqualTo("gabrielamartinez.19@gmail.com");
//	}

	
	
	@Test
	public void deveDeletarCliente() {
		Cliente cliente = new Cliente("Gabriela", "998189815", "gabrielamartinez.19@gmail.com"); 
		clienteService.save(cliente);
		clienteService.deleteById(cliente.getId());
		Assertions.assertThat(clienteService.findById(cliente.getId())).isNull();
		
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
