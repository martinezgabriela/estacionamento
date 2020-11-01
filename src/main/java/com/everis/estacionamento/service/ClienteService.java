package com.everis.estacionamento.service;

import java.util.List;

import com.everis.estacionamento.model.Cliente;


public interface ClienteService {
	
	List<Cliente> findAll();
	Cliente findById(Long id);
	Cliente save(Cliente cliente);
	

}
