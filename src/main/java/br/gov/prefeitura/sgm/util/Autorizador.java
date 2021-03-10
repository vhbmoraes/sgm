package br.gov.prefeitura.sgm.util;

import java.io.IOException;

import javax.faces.application.NavigationHandler;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class Autorizador implements PhaseListener {

	private static final long serialVersionUID = 1L;

	@Override
    public void afterPhase(PhaseEvent event) {
		FacesContext context = event.getFacesContext();
	    String nomePagina = context.getViewRoot().getViewId();

	    if ("/index.xhtml".equals(nomePagina)) {
	        return;
	    }

	    String usuarioLogado = (String) context.getExternalContext().getSessionMap().get("usuarioLogado");

	    if(usuarioLogado != null) {
	        return;
	    }

	    NavigationHandler handler = context.getApplication().getNavigationHandler();
	    handler.handleNavigation(context, null, "/login?faces-redirect=true");
	    
	    ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
    	try {
			ec.redirect(ec.getRequestContextPath() + "/index.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}

	    context.renderResponse();
    }

    @Override
    public void beforePhase(PhaseEvent event) {
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
}
