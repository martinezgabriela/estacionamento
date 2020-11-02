package com.everis.estacionamento.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.everis.estacionamento.model.Cliente;
import com.everis.estacionamento.service.ClienteService;


@Controller
public class ClienteController {

	@Autowired
	ClienteService clienteService;
	
	
	
	@RequestMapping("/")
    public String index(){
        return "index";
    }
	
	
	
	 @RequestMapping(value="/salvarcliente", method=RequestMethod.GET)
	  public String getClienteForm() {
		  return "salvarcliente";
	  }

	@RequestMapping(value="/salvarcliente", method=RequestMethod.POST)
	public String salvarClienteNoBanco(@Valid Cliente cliente, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique se os campos obrigatórios foram inseridos.");
			return "redirect:/salvarcliente";
		}
		clienteService.save(cliente);
		attributes.addFlashAttribute("mensagemSucesso", "Cadastro realizado com sucesso");
		return "redirect:/salvarcliente";
	}
	
	
	@RequestMapping(value="/listarclientes",  method = RequestMethod.GET)
	public String listarClientes(Model model) {
		
		Iterable<Cliente> clientes = clienteService.findAll(); //
		model.addAttribute("clientes", clientes);			
		return "listarclientes";
	}
	
	
	
	

}


