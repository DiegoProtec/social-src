package br.com.social.bean;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.social.dao.UsuarioDao;
import br.com.social.modelo.Usuario;

@Model
public class LoginBean {

	private UsuarioBean usuarioBean = new UsuarioBean();
	private Usuario usuario = new Usuario();

	@Inject
	private UsuarioDao usuarioDao;

	@Transactional
	public void logar() {
		setUsuario(usuarioDao.consultaUsuario(getUsuario()));
		if (null != getUsuario()) {
			getUsuarioBean().inicializar(getUsuario());
		}
	}

	@Transactional
	public void deslogar() {
		getUsuarioBean().finalizar();
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

}
