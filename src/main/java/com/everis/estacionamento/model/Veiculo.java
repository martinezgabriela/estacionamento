package com.everis.estacionamento.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table (name="TB_VEICULO")
public class Veiculo {
	
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;
	
	
	private String marca;
	
	
	private String cor;
	
	
	private String placa;
	
	@Enumerated(EnumType.STRING)
	private TipoVeiculo tipoVeiculo;	
	
	
	@JoinColumn(unique=true) 
	@ManyToOne
	private Cliente cliente;
	
	public Veiculo() {
	}
	
	public Veiculo(String marca, String cor, String placa, Cliente cliente) {
		
		this.marca = marca;
		this.cor = cor;
		this.placa = placa;
		this.cliente = cliente;
	}

	public Veiculo(String marca, String cor, String placa, TipoVeiculo tipoVeiculo, Cliente cliente) {
		
		this.marca = marca;
		this.cor = cor;
		this.placa = placa;
		this.tipoVeiculo = tipoVeiculo;
		this.cliente = cliente;
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

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public TipoVeiculo getTipoVeiculo() {
		return tipoVeiculo;
	}

	public void setTipoVeiculo(TipoVeiculo tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	
	
	
	
	
	

}