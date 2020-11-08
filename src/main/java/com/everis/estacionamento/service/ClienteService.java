package com.everis.estacionamento.service;

import java.util.List;
import com.everis.estacionamento.model.Cliente;


public interface ClienteService {
	
	List<Cliente> findAll();
	Cliente findById(Long id);
	Cliente save(Cliente cliente);
	void deleteById(Long id);		
	public List<Cliente> findByNome(String nome);
	public Cliente atualizar(Long id, Cliente clienteAtualizar);
	
	
	
	
	
	
	

}
