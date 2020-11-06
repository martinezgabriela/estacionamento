package com.everis.estacionamento.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.estacionamento.model.Ticket;
import com.everis.estacionamento.repository.TicketRepository;
import com.everis.estacionamento.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {
	
	@Autowired
	TicketRepository ticketRepository;

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

}
