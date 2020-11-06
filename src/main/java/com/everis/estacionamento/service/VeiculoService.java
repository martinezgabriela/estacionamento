package com.everis.estacionamento.service;

import java.util.List;

import com.everis.estacionamento.model.Veiculo;

public interface VeiculoService {
	
	List<Veiculo> findAll();
	List<Veiculo> findByClienteNome(String nomeCliente);
	Veiculo findById(Long id);
	Veiculo save(Veiculo veiculo);
	void delete(Veiculo veiculo);
	

}
