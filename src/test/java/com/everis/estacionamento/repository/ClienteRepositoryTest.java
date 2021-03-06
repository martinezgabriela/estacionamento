package com.everis.estacionamento.repository;

import org.assertj.core.api.Assertions;
import org.junit.Rule;


import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.everis.estacionamento.model.Cliente;
import com.everis.estacionamento.repository.ClienteRepository;
import com.everis.estacionamento.service.ClienteService;
import com.everis.estacionamento.service.impl.ClienteServiceImpl;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ClienteRepositoryTest {
	
	

	@Autowired
	private ClienteRepository clienteRepository;
		


	@Rule // nós vamos dizer quais exceções esperamos no método e caso elas não ocorram
			// nosso teste será falho:
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void devePersistirCliente() {
		Cliente cliente = new Cliente("Gabriela", "99819883", "gab@gmail.com");
		this.clienteRepository.save(cliente);
		Assertions.assertThat(cliente.getId()).isNotNull();
		Assertions.assertThat(cliente.getNome()).isEqualTo("Gabriela");
		Assertions.assertThat(cliente.getTelefone()).isEqualTo("99819883");
		Assertions.assertThat(cliente.getEmail()).isEqualTo("gab@gmail.com");
	}
	
	@Test
	public void deveDarErroAoPersistirClientePorDadosInvalidos() {
		Cliente cliente = new Cliente("Gabriela", "99819883", "gabgmail.com");
		this.clienteRepository.save(cliente);
		Assertions.assertThat(cliente.getId()).isNotNull();
		Assertions.assertThat(cliente.getNome()).isEqualTo("Gabriela");
		Assertions.assertThat(cliente.getTelefone()).isEqualTo("99819883");
		Assertions.assertThat(cliente.getEmail()).isEqualTo("gabgmail.com");
	}
	
	@Test
	public void deveDeletarCliente() {
		Cliente cliente = new Cliente("Gabriela", "99819883", "gab@gmail.com");
		this.clienteRepository.save(cliente);		
		this.clienteRepository.deleteById(cliente.getId());
		Assertions.assertThat(clienteRepository.findById(cliente.getId())).isEmpty();
	}	

	
	@Test
	public void deveAcharClientePorId() {
		Cliente cliente = new Cliente("Gabriela", "99819883", "gab@gmail.com");
		cliente = this.clienteRepository.save(cliente);
		Cliente cliente2 = this.clienteRepository.findById(cliente.getId()).get();
		Assertions.assertThat(cliente2.getId()).isEqualTo(cliente.getId());		
	}

}
		
	
	