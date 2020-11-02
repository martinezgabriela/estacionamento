package com.everis.estacionamento.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.estacionamento.model.Veiculo;
import com.everis.estacionamento.repository.VeiculoRepository;

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
		return veiculoRepository.findById(id).get();
	}

	@Override
	public Veiculo save(Veiculo veiculo) {
		return veiculoRepository.save(veiculo);
	}

	@Override
	public void deleteById(Veiculo veiculo) {
		veiculoRepository.delete(veiculo);		
	}

}
