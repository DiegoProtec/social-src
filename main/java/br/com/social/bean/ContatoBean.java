package br.com.social.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.social.dao.ContatoDao;
import br.com.social.dao.UsuarioDao;
import br.com.social.modelo.Contato;
import br.com.social.modelo.Usuario;

@Model
public class ContatoBean {

	private ControladorBean bean = new ControladorBean();
	private List<Contato> contatos = new ArrayList<>();
	private List<Usuario> usuarios = new ArrayList<>();
	private FacesContext context;
	private Map<String, String> params;
	private String pagina;

	@Inject
	private ContatoDao contatoDao;

	@Inject
	private UsuarioDao usuarioDao;

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

	@Transactional
	public void procurar(String email) {
		setUsuarios(usuarioDao.procura(email));
		if (null != getUsuarios()) {
			getContext().getExternalContext().getSessionMap().put("usuarios", getUsuarios());
		} else {
			getContext().addMessage(null, new FacesMessage("Usuário: " + email + ", não encontrado."));
		}
		getContext().getExternalContext().getFlash().setKeepMessages(true);
	}

	@Transactional
	public void adicionar() {

	}

	private void setContatos(List<Contato> contatos) {
		this.contatos = contatos;

	}

	public List<Contato> getContatos() {
		return this.contatos;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	private void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
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
