package com.everis.estacionamento.configuracao.validacao;

public class ErrorDeFormularioDto {
	
	private String campo;
	private String erro;
	
	public ErrorDeFormularioDto(String campo, String erro) {
		
		this.campo = campo;
		this.erro = erro;
	}

	public String getErro() {
		return erro;
	}
	public void setErro(String erro) {
		this.erro = erro;
	}
	public String getCampo() {
		return campo;
	}
}
