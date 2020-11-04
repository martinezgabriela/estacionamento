package com.everis.estacionamento.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.everis.estacionamento.model.Cliente;


public interface ClienteService {
	
	Page<Cliente> findAll(Pageable paginacao);
	Cliente findById(Long id);
	//o método save é usado tanto para salvar quanto para fazer o update de um dado
	Cliente save(Cliente cliente);
	void deleteById(Cliente cliente);
	Page<Cliente> findByNome(String cliente, Pageable paginacao);
	
	
	
	
	

}
