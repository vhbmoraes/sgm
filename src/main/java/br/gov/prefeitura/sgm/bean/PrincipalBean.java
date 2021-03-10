package br.gov.prefeitura.sgm.bean;

import java.net.URI;
import java.net.URISyntaxException;

import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@SessionScoped
public class PrincipalBean {
	
	@Value("${app.producao}")
	private String producao;
	
	@Value("${app.port.mimg}")
	private String portMimg;
	
	@Value("${app.port.msc}")
	private String portMsc;
	
	public String getUrlMimg() {
		return retornarPath(portMimg);
	}
	
	public String getUrlMsc() {
		return retornarPath(portMsc);
	}
	
	private String retornarPath(String porta) {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) ec.getRequest();
        URI uri;
		try {
			uri = new URI(request.getRequestURL().toString());
			String url = uri.getScheme() + "://" + uri.getHost() + ":";
			
			if ("true".equals(producao))
				url = url + uri.getPort();
			else
				url = url + porta;
			
			return url;
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
        return null;
	}
	
	public String retorna()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		String loken = (String) context.getExternalContext().getSessionMap().get("usuarioLogado");
		return loken;
	}
}
