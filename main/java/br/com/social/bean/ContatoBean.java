package br.com.social.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.social.dao.UsuarioDao;
import br.com.social.modelo.Contato;
import br.com.social.modelo.Usuario;

@Model
public class ContatoBean {

	private ControladorBean bean = new ControladorBean();
	private Usuario usuario = new Usuario();
	private Usuario contato = new Usuario();
	private List<Contato> contatos = null;
	private FacesContext context;
	private Map<String, String> params;
	private String param;
	List<String> campos = new ArrayList<>();

	@Inject
	private UsuarioDao usuarioDao;

	public ContatoBean() {
		this.context = FacesContext.getCurrentInstance();
	}

	@Transactional
	public void listaContatos() {
		setUsuario((Usuario) getContext().getExternalContext().getSessionMap().get("usuarioLogado"));
		setParams(getContext().getExternalContext().getRequestParameterMap());
		setParam(getParams().get("pagina"));
		// setContatos(getUsuario().getContatos());
		// if (null != getContatos()) {
		// getContext().getExternalContext().getSessionMap().put("contatos",
		// getContatos());
		// } else {
		// System.out.println("você não tem contato adicionado");
		// }
		bean.navegar(getParam());
	}

	public void procurarEmail(AjaxBehaviorEvent ev) {
		setUsuario((Usuario) getContext().getExternalContext().getSessionMap().get("usuarioLogado"));
		getContato().setEmail(getContato().getEmail().trim());
		if (!(getContato().getEmail().equals(getUsuario().getEmail()))) {
			System.out.println("usuários diferentes");
			setContato(usuarioDao.procuraEmail(getContato()));
			if (null != getContato()) {
				getContext().getExternalContext().getSessionMap().put("contato", getContato());
			} else {
				System.out.println("usuario não encontrado.");
			}
		} else {
			setContato(null);
		}
		System.out.println(getContato().getNome());
	}

	@Transactional
	public void adicionar() {
		System.out.println(getContato().getId());
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getContato() {
		return contato;
	}

	public void setContato(Usuario contato) {
		this.contato = contato;
	}

	public List<Contato> getContatos() {
		return this.contatos;
	}

	private void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}

	public Map<String, String> getParams() {
		return this.params;
	}

	public List<String> getCampos() {
		return campos;
	}

	public void setCampos(List<String> campos) {
		this.campos = campos;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}

	public String getParam() {
		return this.param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public FacesContext getContext() {
		return this.context;
	}

}
