package br.com.social.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.social.dao.ContatoDao;
import br.com.social.dao.UsuarioDao;
import br.com.social.modelo.Contato;
import br.com.social.modelo.Usuario;

@Named
@ViewScoped
public class ContatoBean {

	private ControladorBean bean = new ControladorBean();
	private Usuario usuarioLogado = new Usuario();
	private Usuario usuario = new Usuario();
	private Contato contato = new Contato();
	private List<Contato> contatos = new ArrayList<>();
	private FacesContext context;
	private String param;

	@Inject
	private UsuarioDao usuarioDao;

	@Inject
	private ContatoDao contatoDao;

	public ContatoBean() {
		this.context = FacesContext.getCurrentInstance();
	}

	@Transactional
	public void adicionar() {
		setUsuarioLogado((Usuario) getContext().getExternalContext().getSessionMap().get("usuarioLogado"));
		setUsuario(usuarioDao.consultaUsuario(getUsuario().getEmail()));
		getContato().setUsuario(getUsuarioLogado());
		getContato().setContato(getUsuario());
		contatoDao.salvar(getContato());
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

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public List<Contato> getContatos() {
		return this.contatos;
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
