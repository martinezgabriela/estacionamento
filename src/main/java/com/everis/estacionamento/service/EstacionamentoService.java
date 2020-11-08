package com.everis.estacionamento.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.everis.estacionamento.controller.dto.EstacionamentoDtoParaReceber;
import com.everis.estacionamento.model.Estacionamento;

public interface EstacionamentoService {
	
	Optional<Estacionamento> findById(Long id);
	void deleteById(Long id);
	Estacionamento save(EstacionamentoDtoParaReceber estacionamento);
	List<Estacionamento> findAll();
	Estacionamento atualizar(Long id, EstacionamentoDtoParaReceber estacionamentoDto);
	boolean estacionamentEstaCheio(Estacionamento estacionamento);
}
