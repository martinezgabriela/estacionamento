package com.everis.estacionamento.controller.dto;

import javax.validation.constraints.NotBlank;

public class VeiculoDtoParaReceber {
	
	
	private String placa;
	
	@NotBlank
	private String marca;
	
	@NotBlank
	private String cor;		
	
	
	@NotBlank
	private String tipoVeiculo;
	
	@NotBlank
	private String idCliente;
	
	

	public String getIdCliente() {
		return idCliente;
	}


	public String getPlaca() {
		return placa;
	}

	public String getMarca() {
		return marca;
	}

	public String getCor() {
		return cor;
	}

	public String getTipoVeiculo() {
		return tipoVeiculo;
	}
	

	
	

}
