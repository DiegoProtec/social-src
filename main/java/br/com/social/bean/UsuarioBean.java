package br.com.social.bean;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
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

	@Inject
	private UsuarioDao usuarioDao;

	@Transactional
	public void cadastrar() {
		usuarioDao.salvar(usuario);
		System.out.println(usuario + " cadastrado com sucesso!");
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			context.getExternalContext()
					.redirect(context.getExternalContext().getRequestContextPath() + "/index.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
