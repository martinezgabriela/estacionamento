package com.everis.estacionamento.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.estacionamento.controller.dto.VeiculoDtoParaReceber;
import com.everis.estacionamento.model.Cliente;
import com.everis.estacionamento.model.TipoVeiculo;
import com.everis.estacionamento.model.Veiculo;
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
	public Optional<Veiculo> findById(Long id) {
		Optional<Veiculo> veiculo = veiculoRepository.findById(id);
		if(veiculo.isPresent()) {
			return Optional.of(veiculo.get());
		} else {
			throw new NoSuchElementException();
		}
	}

	@Override
	public Veiculo save(VeiculoDtoParaReceber veiculoDto) {
		Veiculo veiculoSalvar = new Veiculo();
		setaConteudoVeiculoComValoresVeiculoDto(veiculoDto, veiculoSalvar);
		return veiculoRepository.save(veiculoSalvar);
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
	public List<Veiculo> findByClienteId(Long idCliente) {
		return veiculoRepository.findByClienteId(idCliente);
		
	}
	
		
	public void setaConteudoVeiculoComValoresVeiculoDto(VeiculoDtoParaReceber veiculoDto, Veiculo veiculo) {
		TipoVeiculo tipoVeiculo = TipoVeiculo.valueOf(veiculoDto.getTipoVeiculo().toUpperCase());
		Cliente cliente = clienteService.findById(Long.parseLong((veiculoDto.getIdCliente())));
		veiculo.setPlaca(veiculoDto.getPlaca());
		veiculo.setMarca(veiculoDto.getMarca());
		veiculo.setCor(veiculoDto.getCor());
		veiculo.setTipoVeiculo(tipoVeiculo);		
		veiculo.setCliente(cliente);
	}

	@Override
	public Veiculo atualizar(Long id, VeiculoDtoParaReceber veiculoAtualizar) {
		Veiculo veiculoDB = veiculoRepository.findById(id).get();
		setaConteudoVeiculoComValoresVeiculoDto(veiculoAtualizar, veiculoDB);		
		return veiculoRepository.save(veiculoDB);
	}

	@Override
	public void deleteById(Long id) {
		veiculoRepository.deleteById(id);
		
	}

}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	



