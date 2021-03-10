package br.gov.prefeitura.sgm.bean;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import br.gov.prefeitura.sgm.service.LoginService;

@Component
@RequestScope
public class LoginBean {
	
	@Autowired
	private LoginService loginService;
	
	private String login;
	private String senha;
	
	public void validar() throws IOException
	{
		if (loginService.validar(login, senha))
		{
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getSessionMap().put("usuarioLogado", loginService.retornarToken(login));
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    	ec.redirect(ec.getRequestContextPath() + "/principal.xhtml");
		}
		else
		{
			FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Login inválido"));
		}
	}
	
	public void deslogar() {
	    FacesContext context = FacesContext.getCurrentInstance();
	    context.getExternalContext().getSessionMap().remove("usuarioLogado");
	}
	
	public String getLogin() {
		return login;
	}
	public String getSenha() {
		return senha;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
}
