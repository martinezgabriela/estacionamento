package com.everis.estacionamento.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.everis.estacionamento.model.Cliente;
import com.everis.estacionamento.repository.ClienteRepository;
import com.everis.estacionamento.service.ClienteService;


@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	ClienteRepository clienteRepository;
	
	@Override
	public Page<Cliente> findAll(Pageable paginacao) {
		return clienteRepository.findAll(paginacao);
	}

	@Override
	public Cliente findById(Long id) {
		return clienteRepository.findById(id).get();
	}

	@Override
	public Cliente save(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@Override
	public void deleteById(Cliente cliente) {
		clienteRepository.deleteById(cliente.getId());
		
	}

	@Override
	public Page<Cliente> findByNome(String cliente, Pageable paginacao) {
		Page<Cliente> clienteEncontrado = clienteRepository.findByNome(cliente, paginacao);
		return clienteEncontrado;
	}






	
	

	
	
	
	
	

}
