package com.everis.estacionamento.service.impl;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.everis.estacionamento.controller.dto.ClienteDtoParaReceber;
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
		try {
			clienteRepository.findById(id).get();
		} catch(NoSuchElementException e) {
			e.getMessage();
			return null;
		}
		return clienteRepository.findById(id).get();
	}

	@Override
	public Cliente save(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@Override
	public void deleteById(Long id) {		
		clienteRepository.deleteById(id);		
	}

	@Override
	public Page<Cliente> findByNome(String cliente, Pageable paginacao) {
		Page<Cliente> clienteEncontrado = clienteRepository.findByNome(cliente, paginacao);
		return clienteEncontrado;
	}
	
	public Cliente atualizar(Long id, Cliente clienteAtualizar) {
		Cliente clienteDB = clienteRepository.findById(id).get();
		clienteDB.setEmail(clienteAtualizar.getEmail());
		clienteDB.setNome(clienteAtualizar.getNome());
		clienteDB.setTelefone(clienteAtualizar.getTelefone());
		return clienteRepository.save(clienteDB);
		
	}

	@Override
	public Cliente findByNome(String nome) {
		Cliente clienteEncontrado = clienteRepository.findByNome(nome);
		return clienteEncontrado;
	}






	
	

	
	
	
	
	

}
