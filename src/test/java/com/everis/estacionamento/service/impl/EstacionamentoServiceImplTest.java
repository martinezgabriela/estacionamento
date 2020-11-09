package com.everis.estacionamento.service.impl;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
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

import com.everis.estacionamento.configuracao.exceptions.NaoEPossivelDeleterClienteComVeiculoException;
import com.everis.estacionamento.controller.dto.EstacionamentoDtoParaReceber;
import com.everis.estacionamento.model.Cliente;
import com.everis.estacionamento.model.Estacionamento;
import com.everis.estacionamento.model.Ticket;
import com.everis.estacionamento.model.TipoVeiculo;
import com.everis.estacionamento.model.Veiculo;
import com.everis.estacionamento.repository.EstacionamentoRepository;
import com.everis.estacionamento.repository.TicketRepository;
import com.everis.estacionamento.service.EstacionamentoService;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
class EstacionamentoServiceImplTest {

	@TestConfiguration
	static class EstacionamentoServiceImplTestConfiguration {

		@Bean
		public EstacionamentoService estacionamentoService() {
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
		Estacionamento estacionamento = new Estacionamento(5, 30);
		Cliente cliente = new Cliente("Gabriela", "999889988", "gab@gmail.com");
		Veiculo veiculo = new Veiculo("MGM0009", "fiat", "azul", TipoVeiculo.CARRO, cliente);
		Ticket ticket = new Ticket(veiculo, estacionamento);
		Mockito.when(estacionamentoRepository.findById(estacionamento.getId())).thenReturn(Optional.of(estacionamento));
		List<Ticket> ticketsSemSaidaParaRetornar = new ArrayList<Ticket>();
		ticketsSemSaidaParaRetornar.add(ticket);
		Mockito.when(ticketRepository.findBySaidaAndEstacionamentoId(null, estacionamento.getId()))
				.thenReturn(ticketsSemSaidaParaRetornar);
		boolean estaCheio = estacionamentoService.estacionamentEstaCheio(estacionamento);
		Assertions.assertThat(estaCheio).isEqualTo(false);
	}

	@Test
	public void testaSeEstacionamentoEstaCheioDeveRetornarTrue() {
		Estacionamento estacionamento = new Estacionamento(5, 1);
		Cliente cliente = new Cliente("Gabriela", "999889988", "gab@gmail.com");
		Veiculo veiculo = new Veiculo("MGM0009", "fiat", "azul", TipoVeiculo.CARRO, cliente);
		Ticket ticket = new Ticket(veiculo, estacionamento);
		Mockito.when(estacionamentoRepository.findById(estacionamento.getId())).thenReturn(Optional.of(estacionamento));
		List<Ticket> ticketsSemSaidaParaRetornar = new ArrayList<Ticket>();
		ticketsSemSaidaParaRetornar.add(ticket);
		Mockito.when(ticketRepository.findBySaidaAndEstacionamentoId(null, estacionamento.getId()))
				.thenReturn(ticketsSemSaidaParaRetornar);
		boolean estaCheio = estacionamentoService.estacionamentEstaCheio(estacionamento);
		Assertions.assertThat(estaCheio).isEqualTo(true);
	}

	@Test
	public void testaMetodoFindAll() {
		Estacionamento estacionamento1 = new Estacionamento(5, 10);
		Estacionamento estacionamento2 = new Estacionamento(7, 5);
		Estacionamento estacionamento3 = new Estacionamento(2, 8);
		List<Estacionamento> estacionamentos = new ArrayList<>();
		estacionamentos.add(estacionamento1);
		estacionamentos.add(estacionamento2);
		estacionamentos.add(estacionamento3);

		Mockito.when(estacionamentoRepository.findAll()).thenReturn(estacionamentos);
		Assertions.assertThat(estacionamentoService.findAll()).size().isEqualTo(3);
	}

	@Test
	public void testafindById() {
		Long id = 1L;
		Estacionamento estacionamento1 = new Estacionamento(5, 10);
		Mockito.when(estacionamentoRepository.findById(id)).thenReturn(Optional.of(estacionamento1));
		Assertions.assertThat(estacionamento1.getId()).isEqualTo((estacionamentoRepository.findById(id)).get().getId());

	}

	@Test
	public void testaMetodoDeleteEstacionamentoLancaExcecao() {
		Exception e = assertThrows(NaoEPossivelDeleterClienteComVeiculoException.class, () -> {
			estacionamentoService.deleteById(1L);
		});
		String expectedMsg = "Não é possível deletar estacionamento com ticket atrelado.";
		String actualMsg = e.getMessage();
		assertTrue(actualMsg.contains(expectedMsg));
	}

}
