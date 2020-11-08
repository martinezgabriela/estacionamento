package com.everis.estacionamento.controller.dto;

import javax.validation.constraints.NotBlank;

public class TicketDtoParaReceber {	
	
		
	
	@NotBlank 
	private String idVeiculo;

	@NotBlank 
	private String idEstacionamento;	
	
		

	public TicketDtoParaReceber(@NotBlank String idVeiculo, @NotBlank String idEstacionamento) {
		super();
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
