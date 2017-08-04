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

	@Inject
	private UsuarioDao usuarioDao;

	public Usuario getUsuario() {
		return usuario;
	}

	@Transactional
	public void cadastrar() {
		FacesContext context = FacesContext.getCurrentInstance();

		System.out.println("metodo cadastrar bean");
		usuarioDao.salvar(usuario);
		System.out.println(usuario + " cadastrado com sucesso!");

		try {
			context.getExternalContext().redirect(context.getExternalContext().getRequestContextPath()
					+ "/index.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
