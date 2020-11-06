package com.everis.estacionamento.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.estacionamento.model.Veiculo;
import com.everis.estacionamento.repository.VeiculoRepository;
import com.everis.estacionamento.service.VeiculoService;

@Service
public class VeiculoServiceImpl implements VeiculoService {
	
	@Autowired
	VeiculoRepository veiculoRepository;
	

	@Override
	public List<Veiculo> findAll() {
		return veiculoRepository.findAll();
	}

	@Override
	public Veiculo findById(Long id) {
		try {
			return veiculoRepository.findById(id).get();
		} catch (Exception e) {
			System.out.println(e.getMessage() + e.getCause());
		}
		return null;
	}

	@Override
	public Veiculo save(Veiculo veiculo) {
		return veiculoRepository.save(veiculo);
	}

	@Override
	public void delete(Veiculo veiculo) {
		veiculoRepository.delete(veiculo);		
	}

	@Override
	public List<Veiculo> findByClienteNome(String nomeCliente) {
		return veiculoRepository.findByClienteNome(nomeCliente);
	}

}
