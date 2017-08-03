package br.com.social.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_contato")
public class Contato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "solicitantefk_contato", nullable = false, unique = false)
	private Usuario solicitante;

	@ManyToOne
	@JoinColumn(name = "solicitadofk_contato", nullable = false, unique = false)
	private Usuario solicitado;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(Usuario solicitante) {
		this.solicitante = solicitante;
	}

	public Usuario getSolicitado() {
		return solicitado;
	}

	public void setSolicitado(Usuario solicitado) {
		this.solicitado = solicitado;
	}

}
