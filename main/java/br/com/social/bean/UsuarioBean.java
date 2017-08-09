package br.com.social.bean;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.social.dao.UsuarioDao;
import br.com.social.modelo.Contato;
import br.com.social.modelo.Mensagem;
import br.com.social.modelo.Usuario;

@Model
public class UsuarioBean {

	private ControladorBean bean = new ControladorBean();
	private List<Contato> contatos = new ArrayList<>();
	private List<Mensagem> mensagens = new ArrayList<>();
	private Usuario usuario = new Usuario();
	private FacesContext context;
	private String pagina;

	public UsuarioBean() {
		this.context = FacesContext.getCurrentInstance();
		this.pagina = "/usuario/cadastroUsuario.xhtml";
	}

	@Inject
	private UsuarioDao usuarioDao;

	@Transactional
	public void cadastrar() {
		if (null == usuarioDao.consultaUsuario(getUsuario().getEmail())) {
			usuarioDao.salvar(getUsuario());
			setUsuario(usuarioDao.consultaUsuario(getUsuario()));
			setPagina("/usuario/usuario.xhtml");
			if (null != getUsuario()) {
				getContext().getExternalContext().getSessionMap().put("usuarioLogado", getUsuario());

			}
		}
		bean.navegar(getPagina());
	}

	public void inicializar(Usuario usuario) {
		setUsuario(usuario);
		getContext().getExternalContext().getSessionMap().put("usuarioLogado", getUsuario());
		bean.navegar("/usuario/usuario.xhtml");

	}

	public void finalizar() {
		getContext().getExternalContext().getSessionMap().remove("usuarioLogado");
		bean.navegar("/login.xhtml");
	}

	private void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public List<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}

	public List<Mensagem> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<Mensagem> mensagens) {
		this.mensagens = mensagens;
	}

	private void setPagina(String pagina) {
		this.pagina = pagina;
	}

	public String getPagina() {
		return this.pagina;
	}

	public FacesContext getContext() {
		return context;
	}

}
