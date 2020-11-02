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

	public Veiculo(String marca, String cor, String placa, TipoVeiculo tipoVeiculo, Cliente cliente) {
		
		this.marca = marca;
		this.cor = cor;
		this.placa = placa;
		this.tipoVeiculo = tipoVeiculo;
		this.cliente = cliente;
	}
	
	
	
	
	
	

}