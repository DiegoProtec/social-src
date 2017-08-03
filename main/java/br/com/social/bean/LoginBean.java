package br.com.social.bean;

import br.com.social.modelo.Usuario;

public class LoginBean {

	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public String logar() {
		return "usuario?faces-redirect=true";
	}
}
