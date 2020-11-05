package com.everis.estacionamento.controller.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.everis.estacionamento.model.Cliente;


public class ClienteDtoParaReceber {
	
	
	@NotBlank
	private String nome;
	@NotBlank
	private String telefone;
	@NotBlank @Email
	private String email;
	
	
	public Cliente converter() {
		return new Cliente(nome, telefone, email);
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
