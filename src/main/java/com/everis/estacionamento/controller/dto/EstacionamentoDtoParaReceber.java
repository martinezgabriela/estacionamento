package com.everis.estacionamento.controller.dto;

import javax.validation.constraints.NotNull;

public class EstacionamentoDtoParaReceber {
	
	@NotNull
	private double valorTarifa;
	
	@NotNull
	private int totalVagasEstacionamento;
	
	

	public double getValorTarifa() {
		return valorTarifa;
	}

	public int getTotalVagasEstacionamento() {
		return totalVagasEstacionamento;
	}
	
	
	

}
