package br.com.social.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_mensagem")
public class Mensagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_mensagem")
	private Integer id;
	
	@Lob
	@Column(name="mensagem_mensagem")
	private String mensagem;
	
	@ManyToOne
	@JoinColumn(name="usuariofk_mensagem")
	private Usuario usuario;

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
