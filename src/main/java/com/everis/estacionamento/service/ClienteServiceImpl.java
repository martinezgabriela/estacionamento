package com.everis.estacionamento.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.estacionamento.model.Cliente;
import com.everis.estacionamento.repository.ClienteRepository;


@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	ClienteRepository clienteRepository;
	
	@Override
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	@Override
	public Cliente findById(Long id) {
		return clienteRepository.findById(id).get();
	}

	@Override
	public Cliente save(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	

}
