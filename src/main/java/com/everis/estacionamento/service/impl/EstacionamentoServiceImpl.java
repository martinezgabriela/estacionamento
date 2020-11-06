package com.everis.estacionamento.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.estacionamento.configuracao.validacao.NaoEPossivelDeleterClienteComVeiculoException;
import com.everis.estacionamento.controller.dto.EstacionamentoDtoParaReceber;
import com.everis.estacionamento.model.Estacionamento;
import com.everis.estacionamento.repository.EstacionamentoRepository;
import com.everis.estacionamento.service.EstacionamentoService;
import com.everis.estacionamento.service.TicketService;

@Service
public class EstacionamentoServiceImpl implements EstacionamentoService{

	
	@Autowired
	EstacionamentoRepository estacionamentoRepository;
	
	@Autowired
	TicketService ticketService;
	


	@Override
	public Optional<Estacionamento> findById(Long id) {
		return estacionamentoRepository.findById(id);
	}



	@Override
	public void deleteById(Long id) {
		if(ticketService.findByEstacionamentoId(id)==null) {
			estacionamentoRepository.deleteById(id);		
		} else {
			throw new NaoEPossivelDeleterClienteComVeiculoException("Não é possível deletar cliente com veículo atrelado.");
		}		
	}
	

	@Override
	public Estacionamento save(EstacionamentoDtoParaReceber estacionamento) {
		Estacionamento estacionamentoSalvar = new Estacionamento(estacionamento.getValorTarifa(), estacionamento.getTotalVagasEstacionamento());
		return estacionamentoRepository.save(estacionamentoSalvar);
	}



	@Override
	public List<Estacionamento> findAll() {
		return estacionamentoRepository.findAll();
	}
	
}
