package com.everis.estacionamento.configuracao.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.everis.estacionamento.model.Usuario;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${estacionamento.jwt.expiration}")
	private String expiration;
	
	@Value("${estacionamento.jwt.secret}")
	private String secret;
	

	public String gerarToken(Authentication authentication) {
		Usuario logado = (Usuario) authentication.getPrincipal();
		Date hoje = new Date();
		Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));
		return Jwts.builder().setIssuer("API do Estacionamento")
				.setSubject(logado.getId().toString())
				.setIssuedAt(hoje)
				.setExpiration(dataExpiracao)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}


	public boolean isTokenValido(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
		

}
