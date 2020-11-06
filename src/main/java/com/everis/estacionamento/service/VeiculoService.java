package com.everis.estacionamento.service;

import java.util.List;
import java.util.Optional;

import com.everis.estacionamento.controller.dto.VeiculoDtoParaReceber;
import com.everis.estacionamento.model.Veiculo;

public interface VeiculoService {
	
	List<Veiculo> findAll();
	List<Veiculo> findByClienteNome(String nomeCliente);
	Optional <Veiculo> findById(Long id);
	Veiculo save(VeiculoDtoParaReceber veiculo);
	Veiculo save(Veiculo veiculo);
	void delete(Veiculo veiculo);
	List<Veiculo> findByClienteId(Long idCliente);
	Veiculo atualizar(Long id, VeiculoDtoParaReceber veiculoAtualizar);
	void deleteById(Long id);
	

}
