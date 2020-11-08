package com.everis.estacionamento.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.everis.estacionamento.controller.dto.TicketDtoParaReceber;
import com.everis.estacionamento.model.Estacionamento;
import com.everis.estacionamento.model.Ticket;

public interface TicketService {
	
	Optional<Ticket> findById(Long id);
	List<Ticket> findAll();
	Ticket save(Ticket ticket);
	void deleteById(Long id);		
	public List<Ticket> findByVeiculoPlaca(String placa);
	Ticket save(TicketDtoParaReceber ticketDtoParaReceber);
	List<Ticket> findByEstacionamentoId(Long id);
	Ticket  registraSaida(Ticket ticketAtualizar);
	Double calculaValorEstadia(double duracao, Ticket ticket);
	List<Ticket> findBySaidaAndEstacionamentoId(LocalDateTime saida, Long idEstacionamento);
	int quantidadeDeVagasDisponiveis(Estacionamento estacionamento);
}
