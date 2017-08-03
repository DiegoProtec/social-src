package br.com.social.modelo;

import java.time.LocalDateTime;

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
	@Column(name = "id_mensagem")
	private int id;

	@Lob
	@Column(name = "mensagem_mensagem", nullable = false)
	private String mensagem;

	@Column(name = "hora_envio_mensagem", nullable = false)
	private LocalDateTime horaEnvio;

	@Column(name = "status_mensagem", nullable = false, columnDefinition = "int  0", insertable = false, updatable = true)
	private int status;

	@ManyToOne
	@JoinColumn(name = "id_conversafk", nullable = false)
	private Conversa conversa;

	@ManyToOne
	@JoinColumn(name = "id_usuariofk", nullable = false)
	private Usuario emissor;

	public int getId() {
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

	public LocalDateTime getHora() {
		return horaEnvio;
	}

	public void setHora(LocalDateTime hora) {
		this.horaEnvio = hora;
	}

	public Conversa getConversa() {
		return conversa;
	}

	public void setConversa(Conversa conversa) {
		this.conversa = conversa;
	}

	public Usuario getUsuario() {
		return emissor;
	}

	public void setUsuario(Usuario usuario) {
		this.emissor = usuario;
	}

}
