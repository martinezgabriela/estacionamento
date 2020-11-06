package com.everis.estacionamento.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.estacionamento.configuracao.validacao.NaoEPossivelDeleterClienteComVeiculoException;
import com.everis.estacionamento.model.Cliente;
import com.everis.estacionamento.repository.ClienteRepository;
import com.everis.estacionamento.service.ClienteService;
import com.everis.estacionamento.service.VeiculoService;


@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	VeiculoService veiculoService;
	
	@Override
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	@Override
	public Cliente findById(Long id) {
		try {
			clienteRepository.findById(id).get();
		} catch(Exception e) {
			e.getMessage();				
		}
		return clienteRepository.findById(id).get();
	}

	@Override
	public Cliente save(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@Override
	public void deleteById(Long id) {		
		if(veiculoService.findByClienteId(id)==null) {
			clienteRepository.deleteById(id);		
		} else {
			throw new NaoEPossivelDeleterClienteComVeiculoException("Não é possível deletar cliente com veículo atrelado.");
		}
	}


	
	public Cliente atualizar(Long id, Cliente clienteAtualizar) {
		Cliente clienteDB = clienteRepository.findById(id).get();
		clienteDB.setEmail(clienteAtualizar.getEmail());
		clienteDB.setNome(clienteAtualizar.getNome());
		clienteDB.setTelefone(clienteAtualizar.getTelefone());
		return clienteRepository.save(clienteDB);
		
	}

	@Override
	public List<Cliente> findByNome(String nome) {
		List<Cliente> clienteEncontrado = clienteRepository.findByNome(nome);
		return clienteEncontrado;
	}






	
	

	
	
	
	
	

}
