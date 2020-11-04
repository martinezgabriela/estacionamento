package com.everis.estacionamento;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.everis.estacionamento.model.Cliente;
import com.everis.estacionamento.model.TipoVeiculo;
import com.everis.estacionamento.model.Veiculo;
import com.everis.estacionamento.repository.ClienteRepository;
import com.everis.estacionamento.repository.VeiculoRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class VeiculoRepositoryTest {
	
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	VeiculoRepository veiculoRepository;
	
//	@Test
//	public void testaCriacaoVeiculoComCliente() {
//	Cliente cliente = new Cliente("Gabi", "99819891", "gab@gmail.com");
//	clienteRepository.save(cliente);
//	Veiculo veiculo = new Veiculo("audi", "azul", "mgx9889", TipoVeiculo.CARRO, cliente);
//	veiculoRepository.save(veiculo);
//	Assertions.assertThat(cliente.getId()).isNotNull();
//	Assertions.assertThat(cliente.getNome()).isEqualTo("Gabriela");
//	Assertions.assertThat(cliente.getTelefone()).isEqualTo("99819883");
//	Assertions.assertThat(cliente.getEmail()).isEqualTo("gab@gmail.com");
//		
//	}
}
