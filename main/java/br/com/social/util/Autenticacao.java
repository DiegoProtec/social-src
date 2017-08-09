package br.com.social.util;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class Autenticacao implements PhaseListener {

	private static final long serialVersionUID = 1L;
	private String pagina;

	@Override
	public void afterPhase(PhaseEvent evento) {
		FacesContext context = evento.getFacesContext();
		pagina = context.getViewRoot().getViewId();

		if ("/index.xhtml".equals(pagina) || "/login.xhtml".equals(pagina) || "/cadastro.xhtml".equals(pagina)) {
			return;
		}

		if (context.getExternalContext().getSessionMap().get("usuarioLogado") != null) {
			return;
		}

		NavigationHandler handler = context.getApplication().getNavigationHandler();
		handler.handleNavigation(context, null, "/login?faces-redirect=true");
		context.renderResponse();
	}

	@Override
	public void beforePhase(PhaseEvent evento) {

	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
