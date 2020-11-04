package com.everis.estacionamento.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table (name="TB_ESTACIONAMENTO")
public class Estacionamento {
	
		
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private double valorTarifa;
	
	@NotBlank
	private int totalVagasEstacionamento;
	

	public Estacionamento() {		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getValorTarifa() {
		return valorTarifa;
	}
	public void setValorTarifa(double valorTarifa) {
		this.valorTarifa = valorTarifa;
	}
	
		
	public int getTotalVagasEstacionamento() {
		return totalVagasEstacionamento;
	}

	public void setTotalVagasEstacionamento(int totalVagasEstacionamento) {
		this.totalVagasEstacionamento = totalVagasEstacionamento;
	}
	
	

		
	
	
	
	
	
	
	

}
