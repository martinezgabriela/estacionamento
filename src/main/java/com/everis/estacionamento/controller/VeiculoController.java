package com.everis.estacionamento.controller;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.everis.estacionamento.controller.dto.ClienteDtoParaEnviar;
import com.everis.estacionamento.controller.dto.VeiculoDtoParaEnviar;
import com.everis.estacionamento.controller.dto.VeiculoDtoParaReceber;
import com.everis.estacionamento.model.Veiculo;
import com.everis.estacionamento.service.VeiculoService;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {
	
	@Autowired
	VeiculoService veiculoService;
	
	@PostMapping
	@Transactional
	public ResponseEntity<Veiculo> cadastrarVeiculo(@Valid @RequestBody VeiculoDtoParaReceber veiculoDtoParaReceber, 
			UriComponentsBuilder uriBuilder){	
		Veiculo veiculo = veiculoService.save(veiculoDtoParaReceber);
		URI uri = uriBuilder.path("/veiculos/{id}").buildAndExpand(veiculo.getId()).toUri();
		return ResponseEntity.created(uri).body(veiculo);
	}
	
	//metodo que lista veiculos - pode ser usado com filtro por nomeCliente ou sem filtro
	@GetMapping
	public List<VeiculoDtoParaEnviar> listarVeiculos(String nomeCliente){
		if(nomeCliente==null) {
			List<Veiculo> veiculos = veiculoService.findAll();
			return VeiculoDtoParaEnviar.converter(veiculos);
		} else {
			List<Veiculo> veiculos = veiculoService.findByClienteNome(nomeCliente);
			return VeiculoDtoParaEnviar.converter(veiculos);
		}
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<VeiculoDtoParaEnviar> atualizarVeiculo (@PathVariable Long id, 
			@Valid @RequestBody VeiculoDtoParaReceber veiculoAtualizar){
		
		try {
			Veiculo veiculo  = veiculoService.findById(id);
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
			return ResponseEntity.notFound().build();
		}
		Veiculo veiculoAtualizado = veiculoService.atualizar(id, veiculoAtualizar);
		return ResponseEntity.ok(new VeiculoDtoParaEnviar(veiculoAtualizado));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> removerVeiculo (@PathVariable Long id){
		try {
			veiculoService.deleteById(id);
		} catch (EmptyResultDataAccessException | NoSuchElementException e){
			System.out.println(e.getMessage());
			return ResponseEntity.notFound().build();
		}		
		return ResponseEntity.ok().build();		
	}
	
	
	
	
	

}
