package com.everis.estacionamento.controller.dto;

import javax.validation.constraints.NotNull;

public class EstacionamentoDtoParaReceber {
	
	@NotNull
	private double valorTarifa;
	
	@NotNull
	private int totalVagasEstacionamento;
	
	
	

	public EstacionamentoDtoParaReceber(@NotNull double valorTarifa, @NotNull int totalVagasEstacionamento) {
		this.valorTarifa = valorTarifa;
		this.totalVagasEstacionamento = totalVagasEstacionamento;
	}

	public double getValorTarifa() {
		return valorTarifa;
	}

	public int getTotalVagasEstacionamento() {
		return totalVagasEstacionamento;
	}
	
	
	

}
