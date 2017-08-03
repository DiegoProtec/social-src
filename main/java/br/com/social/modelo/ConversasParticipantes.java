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
@Table(name = "rl_conversa_usuario")
public class ConversasParticipantes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_participante")
	private int id;

	@ManyToOne
	@JoinColumn(name = "id_conversafk", nullable = false)
	private Conversa conversa;

	@ManyToOne
	@JoinColumn(name = "id_usuariofk", nullable = false)
	private Usuario participante;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Conversa getConversa() {
		return conversa;
	}

	public void setConversa(Conversa conversa) {
		this.conversa = conversa;
	}

	public Usuario getUsuario() {
		return participante;
	}

	public void setUsuario(Usuario usuario) {
		this.participante = usuario;
	}

}
