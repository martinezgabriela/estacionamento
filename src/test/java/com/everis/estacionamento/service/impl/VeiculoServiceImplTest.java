package com.everis.estacionamento.service.impl;

import java.util.Optional;
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
import com.everis.estacionamento.model.TipoVeiculo;
import com.everis.estacionamento.model.Veiculo;
import com.everis.estacionamento.repository.VeiculoRepository;
import com.everis.estacionamento.service.VeiculoService;


@RunWith(SpringRunner.class)
@SpringBootTest
class VeiculoServiceImplTest {

	@TestConfiguration
	static class VeiculoServiceImplTestConfiguration{
		
		@Bean
		public VeiculoService veiculoService(){
			return new VeiculoServiceImpl();
		}
		
	}
	
	@Autowired
	private VeiculoService veiculoService;
	
	@MockBean
	private VeiculoRepository veiculoRepository;
	
	
	@Test
	public void testandoFindById() {
		Long id = 2L;
		Mockito.when(veiculoRepository.findById(id)).thenReturn(Optional.of(new Veiculo ("MGM1234",
				"fiat", "azul", TipoVeiculo.CARRO, new Cliente ("Gabriela", "998189815", "gabriela@gmail.com"))));
		Assertions.assertThat(veiculoService.findById(id).get().getCor()).isEqualTo("azul");	
	}
	
	@Test
	
	public void testandoSave() {
		
	}
	
	
	
	
	
	

}
