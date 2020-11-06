package com.everis.estacionamento.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<Ticket> cadastrarTicket (@Valid @RequestBody TicketDtoParaReceber ticketDtoParaReceber){
		
	}
	
	
	

}
