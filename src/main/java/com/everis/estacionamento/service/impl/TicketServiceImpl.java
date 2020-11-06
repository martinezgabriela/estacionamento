package com.everis.estacionamento.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	@Override
	public Ticket save(TicketDtoParaReceber ticketDtoParaReceber) {
		Optional<Estacionamento> estacionamento = estacionamentoService.findById(Long.parseLong(ticketDtoParaReceber.getIdEstacionamento()));
		Optional<Veiculo> veiculo = veiculoService.findById(Long.parseLong(ticketDtoParaReceber.getIdVeiculo()));		
		if(estacionamento.isPresent() && veiculo.isPresent()) {
			Ticket ticket = new Ticket (veiculo.get(), estacionamento.get());
			return ticketRepository.save(ticket);
		} else {
			throw new NoSuchElementException();
		}
	}

	@Override
	public List<Ticket> findByEstacionamentoId(Long id) {
		return ticketRepository.findByEstacionamentoId(id);
	}



}
