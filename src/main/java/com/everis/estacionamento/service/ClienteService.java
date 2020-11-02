package com.everis.estacionamento.service;

import java.util.List;

import com.everis.estacionamento.model.Cliente;


public interface ClienteService {
	
	List<Cliente> findAll();
	Cliente findById(Long id);
	//o método save é usado tanto para salvar quanto para fazer o update de um dado
	Cliente save(Cliente cliente);
	void deleteById(Cliente cliente);
	
	
	
	
	

}
