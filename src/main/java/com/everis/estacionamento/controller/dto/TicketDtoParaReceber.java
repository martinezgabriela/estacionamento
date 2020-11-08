package com.everis.estacionamento.controller.dto;

import javax.validation.constraints.NotBlank;

public class TicketDtoParaReceber {	
	
		
	
	@NotBlank 
	private String idVeiculo;

	@NotBlank 
	private String idEstacionamento;	
			


	public TicketDtoParaReceber(String idVeiculo, String idEstacionamento) {		
		this.idVeiculo = idVeiculo;
		this.idEstacionamento = idEstacionamento;
	}

	public String getIdVeiculo() {
		return idVeiculo;
	}

	public String getIdEstacionamento() {
		return idEstacionamento;
	}
	
	

}
