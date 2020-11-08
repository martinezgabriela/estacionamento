package com.everis.estacionamento.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.everis.estacionamento.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	public List<Cliente> findByNome(String nome);
			
	public Page<Cliente> findAll(Pageable paginacao);
	
	
}

