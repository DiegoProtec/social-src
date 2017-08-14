package br.com.social.modelo;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rl_mensagem_usuario")
public class MensagensParticipantes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private LocalDateTime horaRecebida;

	private LocalDateTime horaVisualizada;

	@ManyToOne
	private Mensagem mensagem;

	@ManyToOne
	private Usuario destinatario;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getHoraRecebida() {
		return horaRecebida;
	}

	public void setHoraRecebida(LocalDateTime horaRecebida) {
		this.horaRecebida = horaRecebida;
	}

	public LocalDateTime getHoraVisualizada() {
		return horaVisualizada;
	}

	public void setHoraVisualizada(LocalDateTime horaVisualizada) {
		this.horaVisualizada = horaVisualizada;
	}

	public Mensagem getMensagem() {
		return mensagem;
	}

	public void setMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
	}

	public Usuario getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Usuario destinatario) {
		this.destinatario = destinatario;
	}

}
