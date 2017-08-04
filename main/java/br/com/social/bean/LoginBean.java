package br.com.social.bean;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.social.dao.UsuarioDao;
import br.com.social.modelo.Usuario;

@Named
@RequestScoped
public class LoginBean {

	private Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}

	@Inject
	private UsuarioDao usuarioDao;

	@Transactional
	public void logar() {
		FacesContext context = FacesContext.getCurrentInstance();
		boolean existe = usuarioDao.consultaUsuario(usuario);
		String pagina = "";

		try {
			if (existe) {
				context.getExternalContext().getSessionMap().put("usuarioLogado", this.usuario);
				pagina = "/usuario/usuario.xhtml";
			} else {
				context.addMessage(null, new FacesMessage("Usuário não encontrado."));
				pagina = "/index.xhtml";
			}
			context.getExternalContext().getFlash().setKeepMessages(true);
			context.getExternalContext().redirect(context.getExternalContext().getRequestContextPath() + pagina);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Transactional
	public void deslogar() {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		try {
			context.getSessionMap().remove("usuarioLogado");
			context.redirect(context.getRequestContextPath() + "/index.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
