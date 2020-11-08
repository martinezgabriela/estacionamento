package com.everis.estacionamento.service.impl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.estacionamento.configuracao.exceptions.EstacionamentoCheioException;
import com.everis.estacionamento.controller.dto.TicketDtoParaReceber;
import com.everis.estacionamento.model.Estacionamento;
import com.everis.estacionamento.model.Ticket;
import com.everis.estacionamento.model.Veiculo;
import com.everis.estacionamento.repository.TicketRepository;
import com.everis.estacionamento.service.EstacionamentoService;
import com.everis.estacionamento.service.TicketService;
import com.everis.estacionamento.service.VeiculoService;

import ch.qos.logback.core.util.Duration;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	TicketRepository ticketRepository;

	@Autowired
	EstacionamentoService estacionamentoService;

	@Autowired
	VeiculoService veiculoService;

	@Override
	public Optional<Ticket> findById(Long id) {
		return ticketRepository.findById(id);
	}

	@Override
	public List<Ticket> findAll() {
		return ticketRepository.findAll();
	}

	@Override
	public Ticket save(Ticket ticket) {
		return ticketRepository.save(ticket);
	}

	@Override
	public void deleteById(Long id) {
		ticketRepository.deleteById(id);

	}

	@Override
	public List<Ticket> findByVeiculoPlaca(String placa) {
		return ticketRepository.findByVeiculoPlaca(placa);
	}

	public boolean verificaSePodeCriarTicket(TicketDtoParaReceber ticketDtoParaReceber) {
		Optional<Estacionamento> estacionamentoOptional = estacionamentoService
				.findById(Long.parseLong(ticketDtoParaReceber.getIdEstacionamento()));
		if (estacionamentoOptional.isPresent()) {
			return quantidadeDeVagasDisponiveis(estacionamentoOptional.get()) > 0;
		} else {
			throw new NoSuchElementException("Estacionamento não encontrado.");
		}
	}

	@Override
	public Ticket save(TicketDtoParaReceber ticketDtoParaReceber) {
		if (verificaSePodeCriarTicket(ticketDtoParaReceber)) {
			Optional<Estacionamento> estacionamento = estacionamentoService
					.findById(Long.parseLong(ticketDtoParaReceber.getIdEstacionamento()));
			Optional<Veiculo> veiculo = veiculoService.findById(Long.parseLong(ticketDtoParaReceber.getIdVeiculo()));
			if (estacionamento.isPresent() && veiculo.isPresent()) {
				Ticket ticket = new Ticket(veiculo.get(), estacionamento.get());
				return ticketRepository.save(ticket);
			} else {
				throw new NoSuchElementException();
			}
		}
		throw new EstacionamentoCheioException("O estacionamento está lotado.");
	}

	@Override
	public List<Ticket> findByEstacionamentoId(Long id) {
		return ticketRepository.findByEstacionamentoId(id);
	}

	public Double calculaDuracaoEstadia(Ticket ticket) {
		LocalDateTime entrada = ticket.getEntrada();
		LocalDateTime saida = ticket.getSaida();
		LocalDateTime from = LocalDateTime.of(entrada.getYear(), entrada.getMonth(), entrada.getDayOfMonth(),
				entrada.getHour(), entrada.getMinute());
		LocalDateTime to = LocalDateTime.of(saida.getYear(), saida.getMonth(), saida.getDayOfMonth(), saida.getHour(),
				saida.getMinute());
		double minutes = ChronoUnit.MINUTES.between(from, to);
		return minutes;

	}

	public Double calculaValorEstadia(double duracao, Ticket ticket) {
		double valorTarifaPor60Min = ticket.getEstacionamento().getValorTarifa();
		double valorEstadia = (valorTarifaPor60Min * duracao) / 60;
		return valorEstadia;
	}

	@Override
	public Ticket registraSaida(Ticket ticketAtualizar) {
		ticketAtualizar.setSaida(LocalDateTime.now());
		double duracaoEstadia = calculaDuracaoEstadia(ticketAtualizar);
		double valorEstadia = calculaValorEstadia(duracaoEstadia, ticketAtualizar);
		ticketAtualizar.setValorEstadia(valorEstadia);
		return ticketRepository.save(ticketAtualizar);
	}

	public int quantidadeDeVagasDisponiveis(Estacionamento estacionamento) {

		List<Ticket> carrosEstacionadosNesteMomento = ticketRepository.findBySaidaAndEstacionamentoId(null, estacionamento.getId());
		if (carrosEstacionadosNesteMomento.isEmpty()) {
			return estacionamento.getTotalVagasEstacionamento();
		} else {
			return estacionamento.getTotalVagasEstacionamento() - carrosEstacionadosNesteMomento.size();
		}
	}

	@Override
	public List<Ticket> findBySaidaAndEstacionamentoId(LocalDateTime saida, Long idEstacionamento) {
		return ticketRepository.findBySaidaAndEstacionamentoId(saida, idEstacionamento);
	}

}
