package com.everis.estacionamento.service.impl;

import java.util.ArrayList;
import java.util.List;
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

import com.everis.estacionamento.controller.dto.TicketDtoParaReceber;
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
	
	@MockBean
	private EstacionamentoService estacionamentoService;
	
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
		Assertions.assertThat(ticketService.calculaValorEstadia(70, ticket)).isNotEqualTo(estacionamento.getValorTarifa());
	}
	
	@Test
	public void testaVerificaSePodeCriarTicketDeveRetornarOptionalDoEstacionamento() {
		Estacionamento estacionamento = new Estacionamento(5, 1);
		TicketDtoParaReceber ticketDto = new TicketDtoParaReceber("1", "1");
		Optional<Estacionamento> estacionamentoOptional = Optional.of(estacionamento);
		Mockito.when(estacionamentoService.findById(1L)).thenReturn(estacionamentoOptional);
		Assertions.assertThat(ticketService.verificaSePodeCriarTicket(ticketDto)).isEqualTo(Optional.of(estacionamento));
	}
	
	@Test
	public void testaMetodoQuantidadeDeVagasDisponiveisDeveRetornarZeroPoisEstacionamentoEstaCheio() {
		Estacionamento estacionamento = new Estacionamento(5, 2);
		Cliente cliente = new Cliente ("Gabriela", "999889988", "gab@gmail.com");
		Veiculo veiculo1 = new Veiculo ("XXX1111", "fiat", "azul", TipoVeiculo.CARRO, cliente);
		Veiculo veiculo2 = new Veiculo ("MGM0009", "fiat", "azul", TipoVeiculo.CARRO, cliente);
		Ticket ticket1 = new Ticket (veiculo1, estacionamento);	
		Ticket ticket2 = new Ticket (veiculo2, estacionamento);	
		List <Ticket> tickets = new ArrayList<>();
		tickets.add(ticket1);
		tickets.add(ticket2);
		Mockito.when(ticketRepository.findBySaidaAndEstacionamentoId(null, estacionamento.getId())).thenReturn(tickets);
		Assertions.assertThat(ticketService.quantidadeDeVagasDisponiveis(estacionamento)).isEqualTo(0);
	}
	
	@Test
	public void testaMetodoQuantidadeDeVagasDisponiveisDeveRetornar5PoisHa5Vagas() {
		Estacionamento estacionamento = new Estacionamento(5, 7);
		Cliente cliente = new Cliente ("Gabriela", "999889988", "gab@gmail.com");
		Veiculo veiculo1 = new Veiculo ("XXX1111", "fiat", "azul", TipoVeiculo.CARRO, cliente);
		Veiculo veiculo2 = new Veiculo ("MGM0009", "fiat", "azul", TipoVeiculo.CARRO, cliente);
		Ticket ticket1 = new Ticket (veiculo1, estacionamento);	
		Ticket ticket2 = new Ticket (veiculo2, estacionamento);	
		List <Ticket> tickets = new ArrayList<>();
		tickets.add(ticket1);
		tickets.add(ticket2);
		Mockito.when(ticketRepository.findBySaidaAndEstacionamentoId(null, estacionamento.getId())).thenReturn(tickets);
		Assertions.assertThat(ticketService.quantidadeDeVagasDisponiveis(estacionamento)).isEqualTo(5);
	}
	
	
	@Test
	public void testaVerificaSeOVeiculoTemTicketEmAbertoNesteEstacionamentoDeveRetornarTrue() {
		Estacionamento estacionamento = new Estacionamento(5, 7);
		Cliente cliente = new Cliente ("Gabriela", "999889988", "gab@gmail.com");
		Veiculo veiculo1 = new Veiculo ("XXX1111", "fiat", "azul", TipoVeiculo.CARRO, cliente);
		Veiculo veiculo2 = new Veiculo ("MGM0009", "fiat", "azul", TipoVeiculo.CARRO, cliente);		
		Ticket ticket1 = new Ticket (veiculo1, estacionamento);	
		Ticket ticket2 = new Ticket (veiculo2, estacionamento);	
		List <Ticket> tickets = new ArrayList<>();
		tickets.add(ticket1);
		tickets.add(ticket2);
		Mockito.when(ticketRepository.findBySaidaAndVeiculoIdAndEstacionamentoId(null, 1L, 1L))
		.thenReturn(tickets);
		Assertions.assertThat(ticketService.verificaSeOVeiculoTemTicketEmAbertoNesteEstacionamento(1L, 1L))
		.isEqualTo(true);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
