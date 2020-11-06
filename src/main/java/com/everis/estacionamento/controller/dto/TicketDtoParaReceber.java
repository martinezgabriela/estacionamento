package com.everis.estacionamento.controller.dto;

import javax.validation.constraints.NotBlank;

public class TicketDtoParaReceber {	
	
		
	
	@NotBlank 
	private Long idVeiculo;

	@NotBlank 
	private Long idEstacionamento;

}
