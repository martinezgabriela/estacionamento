package com.everis.estacionamento.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.everis.estacionamento.controller.dto.ClienteDtoParaReceber;
import com.everis.estacionamento.model.Cliente;


public interface ClienteService {
	
	List<Cliente> findAll();
	Cliente findById(Long id);
	Cliente save(Cliente cliente);
	void deleteById(Long id);		
	public List<Cliente> findByNome(String nome);
	public Cliente atualizar(Long id, Cliente clienteAtualizar);
	
	
	
	
	
	
	

}
