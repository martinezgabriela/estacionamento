package com.everis.estacionamento.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.estacionamento.configuracao.exceptions.NaoEPossivelDeletarEstacionamentoComTicketsAtrelados;
import com.everis.estacionamento.configuracao.exceptions.NaoEPossivelDeleterClienteComVeiculoException;
import com.everis.estacionamento.controller.dto.EstacionamentoDtoParaReceber;
import com.everis.estacionamento.model.Estacionamento;
import com.everis.estacionamento.model.Ticket;
import com.everis.estacionamento.repository.EstacionamentoRepository;
import com.everis.estacionamento.service.EstacionamentoService;
import com.everis.estacionamento.service.TicketService;


@Service
public class EstacionamentoServiceImpl implements EstacionamentoService {

	@Autowired
	EstacionamentoRepository estacionamentoRepository;

	@Autowired
	TicketService ticketService;

	@Override
	public Optional<Estacionamento> findById(Long id) {
		return estacionamentoRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		List<Ticket> listaTickets = ticketService.findByEstacionamentoId(id);
		if (listaTickets.isEmpty()) {
			estacionamentoRepository.deleteById(id);
		} else {
			throw new NaoEPossivelDeletarEstacionamentoComTicketsAtrelados(
					"Não é possível deletar estacionamento com ticket atrelado.");
		}
	}

	@Override
	public Estacionamento save(EstacionamentoDtoParaReceber estacionamento) {
		Estacionamento estacionamentoSalvar = new Estacionamento(estacionamento.getValorTarifa(),
				estacionamento.getTotalVagasEstacionamento());
		return estacionamentoRepository.save(estacionamentoSalvar);
	}

	@Override
	public List<Estacionamento> findAll() {
		return estacionamentoRepository.findAll();
	}

	@Override
	public Estacionamento atualizar(Long id, EstacionamentoDtoParaReceber estacionamentoDto) {
		Optional<Estacionamento> estacionamento = estacionamentoRepository.findById(id);
		if (estacionamento.isPresent()) {
			Estacionamento estacionamentoAtualizar = estacionamento.get();
			estacionamentoAtualizar.setTotalVagasEstacionamento(estacionamentoDto.getTotalVagasEstacionamento());
			estacionamentoAtualizar.setValorTarifa(estacionamentoDto.getValorTarifa());
			return estacionamentoRepository.save(estacionamentoAtualizar);
		} else {
			throw new NoSuchElementException("Elemento não encontrado");
		}
	}

	public boolean estacionamentEstaCheio(Estacionamento estac) {
		Optional<Estacionamento> estacOptional = estacionamentoRepository.findById(estac.getId());
		if (!estacOptional.isPresent()) {
			throw new NoSuchElementException();
		} else {
			Estacionamento estacionamento = estacOptional.get();
			return ticketService.quantidadeDeVagasDisponiveis(estacionamento) == 0;

		}
	}

}
