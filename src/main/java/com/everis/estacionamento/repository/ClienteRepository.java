package com.everis.estacionamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.everis.estacionamento.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	
}

