package com.everis.estacionamento.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.everis.estacionamento.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

	List<Ticket> findByVeiculoPlaca(String placa);

	List<Ticket> findByEstacionamentoId(Long id);
	
	List<Ticket> findBySaidaAndEstacionamentoId(LocalDateTime saida, Long idEstacionamento);

}
