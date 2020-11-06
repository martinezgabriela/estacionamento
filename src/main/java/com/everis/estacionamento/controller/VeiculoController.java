package com.everis.estacionamento.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

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
		
		Veiculo veiculo = veiculoDtoParaReceber.converter();
		veiculoService.save(veiculo);
		URI uri = uriBuilder.path("/veiculos/{id}").buildAndExpand(veiculo.getId()).toUri();
		return ResponseEntity.ok().build();
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
	
	
	
	

}
