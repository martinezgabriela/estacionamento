package com.everis.estacionamento.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table (name="TB_ESTACIONAMENTO")
public class Estacionamento {
	
		
	

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	private double valorTarifa;
	
	@NotNull
	private int totalVagasEstacionamento;
	

	public Estacionamento() {		
	}
	
	public Estacionamento(double valorTarifa, int totalVagasEstacionamento) {
		this.valorTarifa = valorTarifa;
		this.totalVagasEstacionamento = totalVagasEstacionamento;
	}
	
	public Estacionamento(Long id, @NotNull double valorTarifa, @NotNull int totalVagasEstacionamento) {
		
		this.id = id;
		this.valorTarifa = valorTarifa;
		this.totalVagasEstacionamento = totalVagasEstacionamento;
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
