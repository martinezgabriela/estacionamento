package com.everis.estacionamento.controller;

import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.everis.estacionamento.configuracao.exceptions.NaoEPossivelDeleterClienteComVeiculoException;
import com.everis.estacionamento.controller.dto.EstacionamentoDtoParaReceber;
import com.everis.estacionamento.model.Estacionamento;
import com.everis.estacionamento.service.EstacionamentoService;

@RestController
@RequestMapping("/estacionamento")
public class EstacionamentoController {

	@Autowired
	EstacionamentoService estacionamentoService;

	@PostMapping
	public ResponseEntity<Estacionamento> salvarEstacionamento(
			@Valid @RequestBody EstacionamentoDtoParaReceber estacionamentoDto) {
		Estacionamento estacionamento = estacionamentoService.save(estacionamentoDto);
		return ResponseEntity.ok().body(estacionamento);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Estacionamento> alterarEstacionamento(
			@Valid @RequestBody EstacionamentoDtoParaReceber estacionamentoDto, @PathVariable Long id) {
		Estacionamento estacionamento = estacionamentoService.atualizar(id, estacionamentoDto);
		return ResponseEntity.ok().body(estacionamento);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> remover(@PathVariable Long id) {
		try {
			estacionamentoService.deleteById(id);
		} catch (EmptyResultDataAccessException | NoSuchElementException
				| NaoEPossivelDeleterClienteComVeiculoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();

	}
}
