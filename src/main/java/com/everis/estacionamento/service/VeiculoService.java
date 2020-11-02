package com.everis.estacionamento.service;

import java.util.List;

import com.everis.estacionamento.model.Veiculo;

public interface VeiculoService {
	
	List<Veiculo> findAll();
	Veiculo findById(Long id);
	//o método save é usado tanto para salvar quanto para fazer o update de um dado
	Veiculo save(Veiculo veiculo);
	void deleteById(Veiculo veiculo);

}
