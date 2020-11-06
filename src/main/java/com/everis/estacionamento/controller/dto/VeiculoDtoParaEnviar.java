package com.everis.estacionamento.controller.dto;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

import com.everis.estacionamento.model.Veiculo;

public class VeiculoDtoParaEnviar {
	
	private Long id;
	private String placa;	
	private String marca;	
	private String cor;		
	private String tipoVeiculo;	
	private String nomeCliente;
	
	public VeiculoDtoParaEnviar (Veiculo veiculo) {
		this.id= veiculo.getId();
		this.placa = veiculo.getPlaca();
		this.marca = veiculo.getMarca();
		this.cor = veiculo.getCor();
		this.tipoVeiculo = veiculo.getTipoVeiculo().toString();
		this.nomeCliente = veiculo.getCliente().getNome();				
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getTipoVeiculo() {
		return tipoVeiculo;
	}

	public void setTipoVeiculo(String tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	
	public static List <VeiculoDtoParaEnviar> converter(List <Veiculo> veiculos) {
		return veiculos.stream().map(VeiculoDtoParaEnviar:: new).collect(Collectors.toList());
		
	}

}
