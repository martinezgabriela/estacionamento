package com.everis.estacionamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.everis.estacionamento.model.Estacionamento;

public interface EstacionamentoRepository extends JpaRepository<Estacionamento, Long> {

}
