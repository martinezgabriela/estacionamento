package com.everis.estacionamento.service.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
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
import com.everis.estacionamento.repository.TicketRepository;
import com.everis.estacionamento.service.TicketService;



@RunWith(SpringRunner.class)
@SpringBootTest
class TicketServiceImplTest {

	@TestConfiguration
	static class TicketServiceImplTestConfiguration{
		
		@Bean
		public TicketService ticketService(){
			return new TicketServiceImpl();
		}
		
	}
	
	@Autowired
	private TicketService ticketService;
	
	@MockBean
	private TicketRepository ticketRepository;
	
	@Test
	public void testacalculoValorEstadiaConsiderandoQueUmaEstadiaDe60MinutosCustaOValorDaTarifa() {
		Estacionamento estacionamento = new Estacionamento(5, 30);
		Cliente cliente = new Cliente ("Gabriela", "999889988", "gab@gmail.com");
		Veiculo veiculo = new Veiculo ("MGM0009", "fiat", "azul", TipoVeiculo.CARRO, cliente);
		Ticket ticket = new Ticket (veiculo, estacionamento);		
		Assertions.assertThat(ticketService.calculaValorEstadia(60, ticket)).isEqualTo(estacionamento.getValorTarifa());
	}
	
	@Test
	public void testacalculoValorEstadiaComInformacoesErradas() {
		Estacionamento estacionamento = new Estacionamento(5, 30);
		Cliente cliente = new Cliente ("Gabriela", "999889988", "gab@gmail.com");
		Veiculo veiculo = new Veiculo ("MGM0009", "fiat", "azul", TipoVeiculo.CARRO, cliente);
		Ticket ticket = new Ticket (veiculo, estacionamento);		
		Assertions.assertThat(ticketService.calculaValorEstadia(65, ticket)).isNotEqualTo(estacionamento.getValorTarifa());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
