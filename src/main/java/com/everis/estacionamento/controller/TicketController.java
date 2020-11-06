package com.everis.estacionamento.controller;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.everis.estacionamento.controller.dto.TicketDtoParaReceber;
import com.everis.estacionamento.model.Ticket;
import com.everis.estacionamento.service.TicketService;

@RestController
@RequestMapping("/tickets")
public class TicketController {
	
	
	
	@Autowired
	TicketService ticketService;
	
	
	@PostMapping
	@Transactional
	public ResponseEntity<Ticket> cadastrarTicket (@Valid @RequestBody TicketDtoParaReceber ticketDtoParaReceber,
			UriComponentsBuilder uriBuilder){		
		try {
		Ticket ticket = ticketService.save(ticketDtoParaReceber);
		URI uri = uriBuilder.path("/tickets/{id}").buildAndExpand(ticket.getId()).toUri();
		return ResponseEntity.created(uri).body(ticket);
		}
		catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	
		@GetMapping
		public List<Ticket> listarTickets(String placaVeiculo){
			if(placaVeiculo==null) {
				List<Ticket> tickets = ticketService.findAll();
				return tickets;
			} else {
				List<Ticket> tickets = ticketService.findByVeiculoPlaca(placaVeiculo);
				return tickets;
			}
		}
		
		
		
		@DeleteMapping("/{id}")
		public ResponseEntity<?> excluirTicket (@PathVariable Long id){
			try {
				ticketService.deleteById(id);
			} catch (EmptyResultDataAccessException | NoSuchElementException e){
				System.out.println(e.getMessage());
				return ResponseEntity.notFound().build();
			}		
			return ResponseEntity.ok().build();		
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
