package com.everis.estacionamento.controller.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;

import com.everis.estacionamento.model.Cliente;
import com.everis.estacionamento.model.TipoVeiculo;
import com.everis.estacionamento.model.Veiculo;
import com.everis.estacionamento.service.ClienteService;

public class VeiculoDtoParaReceber {
	
	
	private String placa;
	
	@NotBlank
	private String marca;
	
	@NotBlank
	private String cor;		
	
	@Enumerated(EnumType.STRING)
	@NotBlank
	private String tipoVeiculo;
	
	@NotBlank
	private String nomeCliente;
	
	@Autowired
	private ClienteService clienteService;
	
	
	public Veiculo converter() {
		
		//Long id = Long.parseLong(idCliente);
		//System.out.println(idCliente);
		Cliente cliente = clienteService.findByNome(nomeCliente);
		
		if (cliente != null) {
			String tipoVeiculoMaiusc = tipoVeiculo.toUpperCase();
			TipoVeiculo tipoVeiculo = TipoVeiculo.valueOf(tipoVeiculoMaiusc);
			return new Veiculo(marca, placa, cor, tipoVeiculo, cliente);
		}
		return null;
		//throw new NullPointerException();
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
	

//	public String getIdCliente() {
//		return idCliente;
//	}
	
	

}
