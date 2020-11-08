package com.everis.estacionamento.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.estacionamento.configuracao.exceptions.EstacionamentoCheioException;
import com.everis.estacionamento.configuracao.exceptions.VeiculoConstaComoEstacionadoException;
import com.everis.estacionamento.controller.dto.TicketDtoParaReceber;
import com.everis.estacionamento.model.Estacionamento;
import com.everis.estacionamento.model.Ticket;
import com.everis.estacionamento.model.Veiculo;
import com.everis.estacionamento.repository.TicketRepository;
import com.everis.estacionamento.service.EstacionamentoService;
import com.everis.estacionamento.service.TicketService;
import com.everis.estacionamento.service.VeiculoService;

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

	public Optional<Estacionamento> verificaSePodeCriarTicket(TicketDtoParaReceber ticketDtoParaReceber) {
		Optional<Estacionamento> estacionamentoOptional = estacionamentoService
				.findById(Long.parseLong(ticketDtoParaReceber.getIdEstacionamento()));
		if (estacionamentoOptional.isPresent()) {
			if(quantidadeDeVagasDisponiveis(estacionamentoOptional.get()) > 0) {
				return estacionamentoOptional;
			}
			return null;
		} else {
			throw new NoSuchElementException("Estacionamento não encontrado.");
		}
	}
	
	

	@Override
	public Ticket save(TicketDtoParaReceber ticketDtoParaReceber) {
		Optional<Estacionamento> estacionamento = verificaSePodeCriarTicket(ticketDtoParaReceber);
		if (estacionamento != null) {
			Optional<Veiculo> veiculo = veiculoService.findById(Long.parseLong(ticketDtoParaReceber.getIdVeiculo()));
			if (estacionamento.isPresent() && veiculo.isPresent()) {
				
				if(verificaSeOVeiculoTemTicketEmAbertoNesteEstacionamento(veiculo.get().getId(), estacionamento.get().getId()) == false) {
					Ticket ticket = new Ticket(veiculo.get(), estacionamento.get());
					return ticketRepository.save(ticket);
				}else {
					//lançar um erro dizendo que o veículo entrou mas não saiu
					throw new VeiculoConstaComoEstacionadoException("Este veículo consta com estacionado no momento.");
				}
				
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

	public long calculaDuracaoEstadia(Ticket ticket) {
		LocalDateTime entrada = ticket.getEntrada();
		LocalDateTime saida = ticket.getSaida();
		return entrada.until(saida, ChronoUnit.MINUTES);
	}

	public Double calculaValorEstadia(long duracao, Ticket ticket) {
		double valorTarifaPor60Min = ticket.getEstacionamento().getValorTarifa();
		double valorEstadia = Math.round((valorTarifaPor60Min * duracao) / 60);
		return valorEstadia;
	}

	@Override
	public Ticket registraSaida(Ticket ticketAtualizar) {
		ticketAtualizar.setSaida(LocalDateTime.now(ZoneOffset.UTC));
		long duracaoEstadia = calculaDuracaoEstadia(ticketAtualizar);
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
	
	public boolean verificaSeOVeiculoTemTicketEmAbertoNesteEstacionamento(Long idVeiculo, Long idEstacionamento) {
		List<Ticket> ticketsSemSaidaDesteVeiculoNesteEstacionamento = ticketRepository.
				findBySaidaAndVeiculoIdAndEstacionamentoId(null, idVeiculo, idEstacionamento);
		return ticketsSemSaidaDesteVeiculoNesteEstacionamento.size()>0;
	}
	
 

	@Override
	public List<Ticket> findBySaidaAndVeiculoIdAndEstacionamentoId(LocalDateTime saida, Long idVeiculo, Long idEstacionamento) {
		return ticketRepository.findBySaidaAndVeiculoIdAndEstacionamentoId(saida, idVeiculo, idEstacionamento);
	}

}
