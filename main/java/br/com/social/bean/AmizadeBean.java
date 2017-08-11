package br.com.social.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.social.dao.ContatoDao;
import br.com.social.dao.UsuarioDao;
import br.com.social.modelo.Amizade;
import br.com.social.modelo.Usuario;

@Named
@RequestScoped
public class AmizadeBean {

	private ControladorBean bean = new ControladorBean();
	private Usuario usuarioLogado = new Usuario();
	private Usuario usuario = new Usuario();
	private Amizade contato = new Amizade();
	private List<Amizade> contatos = new ArrayList<>();
	private FacesContext context;

	@Inject
	private UsuarioDao usuarioDao;

	@Inject
	private ContatoDao contatoDao;

	public AmizadeBean() {
		this.context = FacesContext.getCurrentInstance();
	}

	@Transactional
	public void adicionar() {
		bean.navegar("/usuario/contatos.xhtml");
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Amizade getContato() {
		return contato;
	}

	public void setContato(Amizade contato) {
		this.contato = contato;
	}

	public List<Amizade> getContatos() {
		return contatos;
	}

	public void setContatos(List<Amizade> contatos) {
		this.contatos = contatos;
	}

	public FacesContext getContext() {
		return this.context;
	}

}
