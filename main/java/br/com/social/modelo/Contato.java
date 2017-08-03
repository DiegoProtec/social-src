package br.com.social.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_contato")
public class Contato {

	@Id
	@ManyToOne
	@JoinColumn(name = "id_usuariopk", nullable = false)
	private Usuario contato;

	@ManyToOne
	@JoinColumn(name = "id_usuariofk", nullable = false)
	private Usuario usuario;

	public Usuario getContato() {
		return contato;
	}

	public void setContato(Usuario contato) {
		this.contato = contato;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
