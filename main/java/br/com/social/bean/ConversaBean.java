package br.com.social.bean;

import java.util.Map;

import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.transaction.Transactional;

@Model
public class ConversaBean {

	private ControladorBean bean = new ControladorBean();
	private FacesContext context;
	private Map<String, String> params;
	private String pagina;

	public ConversaBean() {
		this.context = FacesContext.getCurrentInstance();
	}

	@Transactional
	public void listaConversas() {
		setParams(getContext().getExternalContext().getRequestParameterMap());
		setPagina(getParams().get("pagina"));
		bean.navegar(pagina);
	}

	public Map<String, String> getParams() {
		return params;
	}

	private void setParams(Map<String, String> params) {
		this.params = params;
	}

	public String getPagina() {
		return pagina;
	}

	public void setPagina(String pagina) {
		this.pagina = pagina;
	}

	public FacesContext getContext() {
		return context;
	}
}
