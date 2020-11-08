package com.everis.estacionamento.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.everis.estacionamento.model.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

	List<Veiculo> findByClienteNome(String nomeCliente);

	List<Veiculo> findByClienteId(Long idCliente);

}
