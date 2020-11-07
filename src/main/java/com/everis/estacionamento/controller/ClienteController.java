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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.everis.estacionamento.configuracao.validacao.NaoEPossivelDeleterClienteComVeiculoException;
import com.everis.estacionamento.controller.dto.ClienteDtoParaEnviar;
import com.everis.estacionamento.controller.dto.ClienteDtoParaReceber;
import com.everis.estacionamento.model.Cliente;
import com.everis.estacionamento.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	ClienteService clienteService;

	@PostMapping
	@Transactional
	public ResponseEntity<Cliente> cadastrarCliente(@Valid @RequestBody ClienteDtoParaReceber clienteDtoParaReceber,
			UriComponentsBuilder uriBuilder) {
		Cliente cliente = clienteDtoParaReceber.converter();
		clienteService.save(cliente);
		URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).body(cliente);
	}

	// com este método posso procurar por nome do cliente, se não passar o parâmetro ele buscará todos os clientes
	@GetMapping  
	public List<ClienteDtoParaEnviar> listaCliente(@RequestParam(required = false) String nomeCliente) {
		if (nomeCliente == null) {
			List<Cliente> clientes = clienteService.findAll();
			return ClienteDtoParaEnviar.converter(clientes);
		} else {
			List<Cliente> cliente = clienteService.findByNome(nomeCliente);
			return ClienteDtoParaEnviar.converter(cliente);
		}
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ClienteDtoParaEnviar> atualizarCliente(@PathVariable Long id,
			@Valid @RequestBody ClienteDtoParaReceber clienteAtualizar) {				
		try {
			Cliente cliente = clienteService.findById(id);			
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
			return ResponseEntity.notFound().build();
		}
		Cliente clienteAtualizado =  clienteService.atualizar(id, clienteAtualizar.converter());
		return ResponseEntity.ok(new ClienteDtoParaEnviar(clienteAtualizado));
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id) {
		try {
			clienteService.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (NaoEPossivelDeleterClienteComVeiculoException e) {
			e.getMessage();
			return ResponseEntity.badRequest().build();
		}

	}		
		
		
	}


