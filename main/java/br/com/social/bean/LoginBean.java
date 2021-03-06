package br.com.social.bean;

import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.social.dao.UsuarioDao;
import br.com.social.modelo.Usuario;

@Model
public class LoginBean {

	private ControladorBean bean = new ControladorBean();
	private UsuarioBean usuarioBean = new UsuarioBean();
	private Usuario usuario = new Usuario();
	private FacesContext context;
	private String pagina;

	@Inject
	private UsuarioDao usuarioDao;

	public LoginBean() {
		setContext(FacesContext.getCurrentInstance());
		setPagina("/login.xhtml");
	}

	@Transactional
	public void logar() {
		setUsuario(usuarioDao.consultarUsuario(getUsuario()));
		if (null != getUsuario()) {
			getContext().getExternalContext().getSessionMap().put("usuarioLogado", getUsuario());
			setPagina("/usuario/usuario.xhtml");
		}
		bean.navegar(getPagina());
	}

	@Transactional
	public void deslogar() {
		getContext().getExternalContext().getSessionMap().remove("usuarioLogado");
		setUsuario(null);
		bean.navegar(getPagina());
	}

	public UsuarioBean getUsuarioBean() {
		return usuarioBean;
	}

	private void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public FacesContext getContext() {
		return context;
	}

	public void setContext(FacesContext context) {
		this.context = context;
	}

	public String getPagina() {
		return pagina;
	}

	public void setPagina(String pagina) {
		this.pagina = pagina;
	}

}
