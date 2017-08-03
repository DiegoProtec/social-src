package br.com.social.bean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.social.dao.UsuarioDao;
import br.com.social.modelo.Usuario;

@Named
@RequestScoped
public class UsuarioBean {

	private Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Inject
	private UsuarioDao usuarioDao;

	@Transactional
	public String cadastrar() {
		usuarioDao.salvar(usuario);
		System.out.println(usuario + " cadastrado com sucesso!");
		return "usuario?faces-redirect=true";
	}

}
