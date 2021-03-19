package br.gov.prefeitura.sgm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.prefeitura.sgm.service.LoginService;
import br.gov.prefeitura.sgm.service.UsuarioDTO;

@RestController
public class AutorizacaoController {
	
	@Autowired
	private LoginService l;
	
	@RequestMapping("/autorizacao") 
	public LoginDTO autorizacao(String token) {
		LoginDTO dto = new LoginDTO();
		dto.setStatus(l.isTokenValido(token)); 
		dto.setMensagem(dto.getStatus() ? "Sucesso" : "Falhou"); 
		dto.setLogin(null);
		return dto;
	} 
	
	/*
	 * @RequestMapping("/autorizacao") public LoginDTO autorizacao(String token) {
	 * if (token == null) return retornaFalha();
	 * 
	 * UsuarioDTO u = UsuarioDTO.getEnum(token); if (u != null &&
	 * !UsuarioDTO.INVALIDO.equals(u)) { LoginDTO dto = new LoginDTO();
	 * dto.setStatus(true); dto.setMensagem("Sucesso"); dto.setLogin(u.getLogin());
	 * return dto; }
	 * 
	 * return retornaFalha(); }
	 */
	
	private LoginDTO retornaFalha()
	{
		LoginDTO dto = new LoginDTO();
		dto.setStatus(false);
		dto.setMensagem("Falhou");
		return dto;
	}
	
	public static class LoginDTO {
		private Boolean status;
		private String mensagem;
		private String login;
		
		public Boolean getStatus() {
			return status;
		}
		public void setStatus(Boolean status) {
			this.status = status;
		}
		public String getMensagem() {
			return mensagem;
		}
		public void setMensagem(String mensagem) {
			this.mensagem = mensagem;
		}
		public String getLogin() {
			return login;
		}
		public void setLogin(String login) {
			this.login = login;
		}
	}	
}