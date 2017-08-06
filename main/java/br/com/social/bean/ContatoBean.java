package br.com.social.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.social.dao.ContatoDao;
import br.com.social.modelo.Contato;

@Model
public class ContatoBean {

	private ControladorBean bean = new ControladorBean();
	private List<Contato> contatos = new ArrayList<>();
	private FacesContext context;
	private Map<String, String> params;
	private String pagina;

	@Inject
	private ContatoDao contatoDao;

	public ContatoBean() {
		this.context = FacesContext.getCurrentInstance();
	}

	@Transactional
	public void listaContatos() {
		setParams(getContext().getExternalContext().getRequestParameterMap());
		setPagina(getParams().get("pagina"));
		setContatos(contatoDao.listaContatos());
		getContext().getExternalContext().getSessionMap().put("listaContatos", getContatos());
		bean.navegar(pagina);
	}

	private void setContatos(List<Contato> contatos) {
		this.contatos = contatos;

	}

	public List<Contato> getContatos() {
		return this.contatos;
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
