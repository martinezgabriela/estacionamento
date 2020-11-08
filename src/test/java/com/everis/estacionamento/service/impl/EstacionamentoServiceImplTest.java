package com.everis.estacionamento.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.assertj.core.util.Arrays;
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
import com.everis.estacionamento.model.Ticket;
import com.everis.estacionamento.model.TipoVeiculo;
import com.everis.estacionamento.model.Veiculo;
import com.everis.estacionamento.repository.EstacionamentoRepository;
import com.everis.estacionamento.repository.TicketRepository;
import com.everis.estacionamento.service.EstacionamentoService;
import com.everis.estacionamento.service.TicketService;


@RunWith(SpringRunner.class)
@SpringBootTest
class EstacionamentoServiceImplTest {

	@TestConfiguration
	static class EstacionamentoServiceImplTestConfiguration{
		
		@Bean
		public EstacionamentoService estacionamentoService(){
			return new EstacionamentoServiceImpl();
		}
		
	}
	
	@Autowired
	private EstacionamentoService estacionamentoService;
	
	@MockBean
	private EstacionamentoRepository estacionamentoRepository;
	
	@MockBean
	private TicketRepository ticketRepository;
	
	

	
	@Test
	public void testaSeEstacionamentoEstaCheioDeveRetornarFalsePoisNaoEsta() {
		Estacionamento estacionamento = new Estacionamento (5, 30);
		Cliente cliente = new Cliente ("Gabriela", "999889988", "gab@gmail.com");
		Veiculo veiculo = new Veiculo ("MGM0009", "fiat", "azul", TipoVeiculo.CARRO, cliente);
		Ticket ticket = new Ticket (veiculo, estacionamento);
		Mockito.when(estacionamentoRepository.findById(estacionamento.getId())).thenReturn(Optional.of(estacionamento));
		List<Ticket> ticketsSemSaidaParaRetornar = new ArrayList<Ticket>();
		ticketsSemSaidaParaRetornar.add(ticket);
		Mockito.when(ticketRepository.findBySaidaAndEstacionamentoId(null, estacionamento.getId())).thenReturn(ticketsSemSaidaParaRetornar);
		boolean estaCheio = estacionamentoService.estacionamentEstaCheio(estacionamento);
		Assertions.assertThat(estaCheio).isEqualTo(false);
	}
	
	
	
	@Test
	public void testaSeEstacionamentoEstaCheioDeveRetornarTrue() {
		Estacionamento estacionamento = new Estacionamento (5, 1);
		Cliente cliente = new Cliente ("Gabriela", "999889988", "gab@gmail.com");
		Veiculo veiculo = new Veiculo ("MGM0009", "fiat", "azul", TipoVeiculo.CARRO, cliente);
		Ticket ticket = new Ticket (veiculo, estacionamento);
		Mockito.when(estacionamentoRepository.findById(estacionamento.getId())).thenReturn(Optional.of(estacionamento));
		List<Ticket> ticketsSemSaidaParaRetornar = new ArrayList<Ticket>();
		ticketsSemSaidaParaRetornar.add(ticket);
		Mockito.when(ticketRepository.findBySaidaAndEstacionamentoId(null, estacionamento.getId())).thenReturn(ticketsSemSaidaParaRetornar);
		boolean estaCheio = estacionamentoService.estacionamentEstaCheio(estacionamento);
		Assertions.assertThat(estaCheio).isEqualTo(true);
	}

}
