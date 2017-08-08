package br.com.social.bean;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.social.dao.UsuarioDao;
import br.com.social.modelo.Usuario;

@Model
public class LoginBean {

	private ControladorBean bean = new ControladorBean();
	private Usuario usuario = new Usuario();
	private FacesContext context;
	private String pagina;

	@Inject
	private UsuarioDao usuarioDao;

	public LoginBean() {
		this.context = FacesContext.getCurrentInstance();
		this.pagina = "/login.xhtml";
	}

	@Transactional
	public void logar() {
		setUsuario(usuarioDao.consultaUsuario(getUsuario()));
		if (null != getUsuario()) {
			getContext().getExternalContext().getSessionMap().put("usuarioLogado", getUsuario());
			setPagina("/usuario/usuario.xhtml");
		} else {
			this.getContext().addMessage(null, new FacesMessage("Usuário não encontrado."));
		}
		getContext().getExternalContext().getFlash().setKeepMessages(true);
		bean.navegar(getPagina());
	}

	@Transactional
	public void deslogar() {
		getContext().getExternalContext().getSessionMap().remove("usuarioLogado");
		bean.navegar(getPagina());
	}

	private void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public String getPagina() {
		return this.pagina;
	}

	private void setPagina(String pagina) {
		this.pagina = pagina;
	}

	public FacesContext getContext() {
		return this.context;
	}

}
