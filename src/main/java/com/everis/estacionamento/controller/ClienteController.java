package com.everis.estacionamento.controller;

import java.net.URI;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.everis.estacionamento.controller.dto.ClienteDto;
import com.everis.estacionamento.model.Cliente;
import com.everis.estacionamento.service.ClienteService;


@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	ClienteService clienteService;
	
	

	@PostMapping
	@Transactional
	public ResponseEntity<Cliente> cadastrarCliente(@Valid @RequestBody Cliente cliente, UriComponentsBuilder uriBuilder) {
		clienteService.save(cliente);
		URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).body(cliente);
	}
	
	@GetMapping
	public Page <ClienteDto> listaCliente(@RequestParam (required=false) String nomeCliente, @PageableDefault (sort="nome", direction=Direction.ASC, page=0, size=15)
									 Pageable paginacao){
		if(nomeCliente==null) {
			Page<Cliente> clientes = clienteService.findAll(paginacao);
			return ClienteDto.converter(clientes);
		} else {
			Page<Cliente> clientes = clienteService.findByNome(nomeCliente, paginacao);
			return ClienteDto.converter(clientes);
		}
	}
	
	
	
	
	
	

}


