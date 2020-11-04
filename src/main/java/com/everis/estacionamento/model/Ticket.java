package com.everis.estacionamento.model;


import java.time.LocalDateTime;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table (name="TB_TICKET")
public class Ticket {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn (name="fk_id_Veiculo")
	@NotBlank 
	private Veiculo veiculo;
	
	@NotBlank
	private LocalDateTime entrada;
	
	@NotBlank
	private LocalDateTime saida;
	
	@NotBlank
	private double valorEstadia;
	
	@ManyToOne
	@JoinColumn (name="fk_id_estacionamento")
	@NotBlank 
	private Estacionamento estacionamento;
	
	public Ticket() {
		
	}	
	

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public LocalDateTime getDiaEntrada() {
		return entrada;
	}

	
	
	

}
