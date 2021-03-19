package br.gov.prefeitura.sgm.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class LoginService {
	
	@Value("${app.jwt.expiration}")
	private String expiration;
	
	@Value("${app.jwt.secret}")
	private String secret;

	public Boolean validar(String login, String senha) {
		UsuarioDTO dto = UsuarioDTO.getEnum(login, senha);
		if (dto != null && !UsuarioDTO.INVALIDO.equals(dto))
			return true;

		return false;
	}

	/*
	 * public String retornarToken(String login) { try { MessageDigest m =
	 * MessageDigest.getInstance("MD5"); m.update(login.getBytes(), 0,
	 * login.length()); return new BigInteger(1, m.digest()).toString(16); } catch
	 * (NoSuchAlgorithmException e) { e.printStackTrace(); } return null; }
	 */
	
	public String retornarToken(String login) {
		return gerarToken(UsuarioDTO.getEnum(login));
	}
	
	public String gerarToken(UsuarioDTO usuarioDTO) {
		Date hoje = new Date();
		Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));
		
		return Jwts.builder()
				.setIssuer(usuarioDTO.getLogin())
				.setSubject(usuarioDTO.getId().toString())
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