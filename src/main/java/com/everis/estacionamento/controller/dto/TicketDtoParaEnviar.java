package com.everis.estacionamento.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.everis.estacionamento.model.Estacionamento;
import com.everis.estacionamento.model.Ticket;
import com.everis.estacionamento.model.Veiculo;

public class TicketDtoParaEnviar {
	
	
	
	
	private Long id;

	private String veiculo;
	
	private LocalDateTime entrada;
		
	private LocalDateTime saida;	
	
	private double valorEstadia;
	
	private Long estacionamento;
	
	
	

	public TicketDtoParaEnviar(Ticket ticket) {		
		this.id = ticket.getId();
		this.veiculo = ticket.getVeiculo().getPlaca();
		this.entrada = ticket.getEntrada();
		this.saida = ticket.getSaida();
		this.valorEstadia = ticket.getValorEstadia();
		this.estacionamento = ticket.getEstacionamento().getId();
	}

	public Long getId() {
		return id;
	}

	public String getVeiculo() {
		return veiculo;
	}

	public LocalDateTime getEntrada() {
		return entrada;
	}

	public LocalDateTime getSaida() {
		return saida;
	}

	public double getValorEstadia() {
		return valorEstadia;
	}

	public Long getEstacionamento() {
		return estacionamento;
	}
	
	public static List<TicketDtoParaEnviar> converter (List<Ticket> tickets){
		return tickets.stream().map(TicketDtoParaEnviar::new).collect(Collectors.toList());
	}
	
	

}
