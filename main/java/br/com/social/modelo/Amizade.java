package br.com.social.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_amizade")
public class Amizade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_amizade")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_convidante", nullable = false)
	private Usuario convidante;

	@ManyToOne
	@JoinColumn(name = "id_convidado", nullable = false)
	private Usuario convidado;

	public Usuario getConvidante() {
		return convidante;
	}

	public void setConvidante(Usuario convidante) {
		this.convidante = convidante;
	}

	public Usuario getConvidado() {
		return convidado;
	}

	public void setConvidado(Usuario convidado) {
		this.convidado = convidado;
	}
}
