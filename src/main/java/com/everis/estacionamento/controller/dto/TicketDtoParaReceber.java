package com.everis.estacionamento.controller.dto;

import javax.validation.constraints.NotBlank;

public class TicketDtoParaReceber {	
	
		
	
	@NotBlank 
	private String idVeiculo;

	@NotBlank 
	private String idEstacionamento;
		

	public String getIdVeiculo() {
		return idVeiculo;
	}

	public String getIdEstacionamento() {
		return idEstacionamento;
	}
	
	

}
