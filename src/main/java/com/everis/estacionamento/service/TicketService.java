package com.everis.estacionamento.service;

import java.util.List;
import java.util.Optional;

import com.everis.estacionamento.model.Ticket;

public interface TicketService {
	
	Optional<Ticket> findById(Long id);
	List<Ticket> findAll();
	Ticket save(Ticket ticket);
	void deleteById(Long id);		
	public List<Ticket> findByVeiculoPlaca(String placa);
	

}
