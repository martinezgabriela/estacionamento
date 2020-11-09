package com.everis.estacionamento.service.impl;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
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

import com.everis.estacionamento.controller.dto.TicketDtoParaReceber;
import com.everis.estacionamento.model.Cliente;
import com.everis.estacionamento.model.Estacionamento;
import com.everis.estacionamento.model.Ticket;
import com.everis.estacionamento.model.TipoVeiculo;
import com.everis.estacionamento.model.Veiculo;
import com.everis.estacionamento.repository.TicketRepository;
import com.everis.estacionamento.service.EstacionamentoService;
import com.everis.estacionamento.service.TicketService;
import com.everis.estacionamento.service.VeiculoService;



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
	
	@MockBean
	private VeiculoService veiculoService;
	
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
	
	@Test
	public void testaFindBySaidaAndVeiculoIdAndEstacionamentoId() {
		Estacionamento estacionamento = new Estacionamento(5, 7);
		Cliente cliente = new Cliente ("Gabriela", "999889988", "gab@gmail.com");
		Veiculo veiculo = new Veiculo ("MGM0009", "fiat", "azul", TipoVeiculo.CARRO, cliente);	
		Mockito.when(ticketRepository.findBySaidaAndVeiculoIdAndEstacionamentoId(null, 1L, 1L))
				.thenReturn(Stream.of(new Ticket (veiculo, estacionamento)).collect(Collectors.toList()));
		Assertions.assertThat(ticketService.findBySaidaAndVeiculoIdAndEstacionamentoId(null, 1L, 1L).size())
		.isEqualTo(1);
	}
	
	@Test
	public void testaFindBySaidaAndEstacionamentoId() {
		Estacionamento estacionamento = new Estacionamento(5, 7);
		Cliente cliente = new Cliente ("Gabriela", "999889988", "gab@gmail.com");
		Veiculo veiculo = new Veiculo ("MGM0009", "fiat", "azul", TipoVeiculo.CARRO, cliente);	
		Mockito.when(ticketRepository.findBySaidaAndEstacionamentoId(null, 1L))
				.thenReturn(Stream.of(new Ticket (veiculo, estacionamento)).collect(Collectors.toList()));
		Assertions.assertThat(ticketService.findBySaidaAndEstacionamentoId(null, 1L).size())
		.isEqualTo(1);
	}
	
	@Test 
	public void testaMetodoSaveDeveLançarExcecaoNoSuchElement() {		
		TicketDtoParaReceber ticketDtoParaReceber = new TicketDtoParaReceber("1", "1");
		Optional<Veiculo> optionalVeiculo = Optional.empty();
		Mockito.when(veiculoService.findById(1L)).thenReturn(optionalVeiculo);
		Exception e = assertThrows(NoSuchElementException.class, () -> {
			ticketService.save(ticketDtoParaReceber);
		});
		String expectedMsg = "Estacionamento não encontrado.";
		String actualMsg = e.getMessage();
		assertTrue(actualMsg.contains(expectedMsg));		
		
	}
	
	@Test
	public void testaMetodoFindByIdDeveRetornarTotalVagasEstacionaentoDoTicket() {
		Long id = 1L;
		Estacionamento estacionamento = new Estacionamento(5, 7);
		Cliente cliente = new Cliente ("Gabriela", "999889988", "gab@gmail.com");
		Veiculo veiculo = new Veiculo ("XXX1111", "fiat", "azul", TipoVeiculo.CARRO, cliente);
		Ticket ticket = new Ticket (veiculo, estacionamento);	
		Mockito.when(ticketRepository.findById(id)).thenReturn(Optional.of(ticket));
		Assertions.assertThat(ticketService.findById(id).get().getEstacionamento().getTotalVagasEstacionamento())
		.isEqualTo(7);
	}
	
	@Test
	public void testaMetodoFindAll() {
		Estacionamento estacionamento = new Estacionamento(5, 7);
		Cliente cliente = new Cliente ("Gabriela", "999889988", "gab@gmail.com");
		Veiculo veiculo1 = new Veiculo ("XXX1111", "fiat", "azul", TipoVeiculo.CARRO, cliente);
		Veiculo veiculo2 = new Veiculo ("MGM0009", "fiat", "azul", TipoVeiculo.CARRO, cliente);		
		Ticket ticket1 = new Ticket (veiculo1, estacionamento);	
		Ticket ticket2 = new Ticket (veiculo2, estacionamento);	
		List <Ticket> tickets = new ArrayList<>();
		tickets.add(ticket1);
		tickets.add(ticket2);
		
		Mockito.when(ticketRepository.findAll()).thenReturn(tickets);
		Assertions.assertThat(ticketService.findAll()).size().isEqualTo(2);
		
	}	
	
	@Test
	public void testaMetodoDeleteTicket() {
		Estacionamento estacionamento = new Estacionamento(5, 7);
		Cliente cliente = new Cliente ("Gabriela", "999889988", "gab@gmail.com");
		Veiculo veiculo = new Veiculo ("XXX1111", "fiat", "azul", TipoVeiculo.CARRO, cliente);
		Ticket ticket = new Ticket (veiculo, estacionamento);	
		ticketService.deleteById(ticket.getId());
		Mockito.verify(ticketRepository, Mockito.times(1)).deleteById(ticket.getId());			
	}



	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
