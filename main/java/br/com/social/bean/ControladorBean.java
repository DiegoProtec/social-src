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

	private Map<String, String> params;
	private FacesContext context;
	private String pagina;

	public ControladorBean() {
		this.context = FacesContext.getCurrentInstance();
		this.pagina = "";
	}

	@Transactional
	public void navegar(String pagina) {
		try {
			getContext().getExternalContext().redirect(getContext().getExternalContext().getRequestContextPath()
					+ pagina);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void navegar() {
		setParams(getContext().getExternalContext().getRequestParameterMap());
		setPagina(getParams().get("pagina"));
		try {
			getContext().getExternalContext().redirect(getContext().getExternalContext().getRequestContextPath()
					+ getPagina());
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		return this.context;
	}

}
