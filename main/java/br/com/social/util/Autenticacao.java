package br.com.social.util;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.social.modelo.Usuario;

public class Autenticacao implements PhaseListener {

	private static final long serialVersionUID = 1L;
	private String pagina;

	@Override
	public void afterPhase(PhaseEvent evento) {
		FacesContext context = evento.getFacesContext();
		pagina = context.getViewRoot().getViewId();
		System.out.println(pagina);

		if ("/index.xhtml".equals(pagina) || "/login.xhtml".equals(pagina) || "/cadastro.xhtml".equals(pagina)) {
			return;
		}

		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");

		if (usuario != null) {
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
