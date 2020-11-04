package com.everis.estacionamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.everis.estacionamento.model.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

}
