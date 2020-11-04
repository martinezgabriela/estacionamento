package com.everis.estacionamento.controller.form;

import javax.validation.constraints.NotBlank;

import com.everis.estacionamento.model.Cliente;
import com.everis.estacionamento.service.ClienteService;

public class AtualizaClienteForm {
	
	@NotBlank
	private String nome;
	@NotBlank
	private String telefone;
	@NotBlank
	private String email;
	
	
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
	
	public Cliente atualizar(Long id, ClienteService clienteService) {
		Cliente cliente = clienteService.findById(id);
		cliente.setNome(this.nome);
		cliente.setTelefone(this.telefone);
		cliente.setEmail(this.email);
		return cliente;
		
	}
	
	

}
