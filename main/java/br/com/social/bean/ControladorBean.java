package br.com.social.bean;

import java.io.IOException;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.transaction.Transactional;

@Named
@RequestScoped
public class ControladorBean {

	private FacesContext context = FacesContext.getCurrentInstance();
	private Map<String, String> params;

	@Transactional
	public void navegar(String pagina) {
		try {
			context.getExternalContext().redirect(context.getExternalContext().getRequestContextPath() + pagina);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void navegar() {
		params = context.getExternalContext().getRequestParameterMap();
		String pagina = params.get("pagina");
		try {
			context.getExternalContext().redirect(context.getExternalContext().getRequestContextPath() + pagina);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
