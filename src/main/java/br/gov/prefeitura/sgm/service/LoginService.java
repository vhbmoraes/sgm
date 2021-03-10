package br.gov.prefeitura.sgm.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;

@Service
public class LoginService {

	public Boolean validar(String login, String senha) {
		UsuarioDTO dto = UsuarioDTO.getEnum(login, senha);
		if (dto != null && !UsuarioDTO.INVALIDO.equals(dto))
			return true;

		return false;
	}

	public String retornarToken(String login) {
		try {
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.update(login.getBytes(), 0, login.length());
			return new BigInteger(1, m.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
}