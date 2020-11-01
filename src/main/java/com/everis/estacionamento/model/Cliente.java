package com.everis.estacionamento.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;




@Entity
@Table (name="TB_CLIENTE")
public class Cliente {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long idCliente;
	
	@NotBlank
	private String nome;
	
	@NotBlank 
	private String telefone;
	
	@NotBlank @Email
	private String email;
	
	
	public Long getIdCliente() {
		return idCliente;
	}
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	

}
