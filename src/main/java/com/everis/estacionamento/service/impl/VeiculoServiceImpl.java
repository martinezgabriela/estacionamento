package com.everis.estacionamento.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.estacionamento.controller.dto.VeiculoDtoParaReceber;
import com.everis.estacionamento.model.Cliente;
import com.everis.estacionamento.model.TipoVeiculo;
import com.everis.estacionamento.model.Veiculo;
import com.everis.estacionamento.repository.ClienteRepository;
import com.everis.estacionamento.repository.VeiculoRepository;
import com.everis.estacionamento.service.ClienteService;
import com.everis.estacionamento.service.VeiculoService;

@Service
public class VeiculoServiceImpl implements VeiculoService {
	
	@Autowired
	VeiculoRepository veiculoRepository;
	
	@Autowired
	ClienteService clienteService;
	

	@Override
	public List<Veiculo> findAll() {
		return veiculoRepository.findAll();
	}

	@Override
	public Veiculo findById(Long id) {
		try {
			return veiculoRepository.findById(id).get();
		} catch (Exception e) {
			System.out.println(e.getMessage() + e.getCause());
		}
		return null;
	}

	@Override
	public Veiculo save(VeiculoDtoParaReceber veiculo) {
		Long idCliente = Long.parseLong(veiculo.getIdCliente());
		Cliente cliente = clienteService.findById(idCliente);
		String tipoVeiculoMaiusc = veiculo.getTipoVeiculo().toUpperCase();
		TipoVeiculo tipoVeiculo = TipoVeiculo.valueOf(tipoVeiculoMaiusc);
		Veiculo veiculoSalvar = new Veiculo(veiculo.getPlaca(), veiculo.getMarca(), veiculo.getCor(), tipoVeiculo, cliente);
		return save(veiculoSalvar);
	}

	@Override
	public void delete(Veiculo veiculo) {
		veiculoRepository.delete(veiculo);		
	}

	@Override
	public List<Veiculo> findByClienteNome(String nomeCliente) {
		return veiculoRepository.findByClienteNome(nomeCliente);
	}

	@Override
	public Veiculo save(Veiculo veiculo) {
		return veiculoRepository.save(veiculo);
	}

	@Override
	public List<Veiculo> findByClienteId(Long idCliente) {
		return veiculoRepository.findByClienteId(idCliente);
	}

	@Override
	public Veiculo atualizar(Long id, VeiculoDtoParaReceber veiculoAtualizar) {
		Veiculo veiculoDB = veiculoRepository.findById(id).get();
		Cliente clienteVeiculo = clienteService.findById(Long.parseLong((veiculoAtualizar.getIdCliente())));
		veiculoDB.setCliente(clienteVeiculo);
		veiculoDB.setCor(veiculoAtualizar.getCor());
		veiculoDB.setMarca(veiculoAtualizar.getMarca());
		veiculoDB.setPlaca(veiculoAtualizar.getPlaca());
		String tipoVeiculoMaiusc = veiculoAtualizar.getTipoVeiculo().toUpperCase();
		TipoVeiculo tipoVeiculo = TipoVeiculo.valueOf(tipoVeiculoMaiusc);
		veiculoDB.setTipoVeiculo(tipoVeiculo);
		return veiculoRepository.save(veiculoDB);
	}

	@Override
	public void deleteById(Long id) {
		veiculoRepository.deleteById(id);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}
