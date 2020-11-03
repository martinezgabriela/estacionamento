package com.everis.estacionamento.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.everis.estacionamento.model.Cliente;
import com.everis.estacionamento.model.Veiculo;
import com.everis.estacionamento.repository.VeiculoRepository;
import com.everis.estacionamento.service.ClienteService;
import com.everis.estacionamento.service.VeiculoService;

@Controller
public class VeiculoController {
	
	@Autowired
	VeiculoService veiculoService;
	
	@Autowired
	ClienteService clienteService;
	
	@RequestMapping(value="/salvarveiculo", method=RequestMethod.GET)
	public String getVeiculoForm(Model model) {
		Iterable<Cliente> clientes = clienteService.findAll(); //
		model.addAttribute("listaclientes", clientes);	
		
		return "salvarveiculo";		
	}
	
	@RequestMapping(value="/salvarveiculo", method=RequestMethod.POST)
	public String salvarVeiculoNoBanco(Model model, @Valid @ModelAttribute("listaclientes") Veiculo veiculo, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique se os campos obrigatórios foram inseridos.");
			return "redirect:/salvarveiculo";
		}
		
//		if(veiculo2 != null) {
//			System.out.println(veiculo2.getMarca());
//			Cliente c = veiculo2.getCliente();
//			if(c != null) {
//				System.out.println(c.getNome());
//			}
//		}
		veiculoService.save(veiculo);
		attributes.addFlashAttribute("mensagemSucesso", "Cadastro realizado com sucesso");
		return "redirect:/salvarveiculo";
	}
	
	
	

}
