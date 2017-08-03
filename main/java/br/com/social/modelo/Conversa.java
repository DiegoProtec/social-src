package br.com.social.modelo;

import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_conversa")
public class Conversa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_conversa")
	private int id;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "conversa", targetEntity = ConversasParticipantes.class, fetch = FetchType.LAZY)
	private List<ConversasParticipantes> participantes;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "conversa", targetEntity = Mensagem.class, fetch = FetchType.LAZY)
	private List<Mensagem> mensagens;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<ConversasParticipantes> getParticipantes() {
		List<ConversasParticipantes> listaSegura = Collections.unmodifiableList(this.participantes);
		return listaSegura;
	}

	public void adicionaParticipantes(ConversasParticipantes participante) {
		this.participantes.add(participante);
	}

	public List<Mensagem> getMensagens() {
		List<Mensagem> listaSegura = Collections.unmodifiableList(this.mensagens);
		return listaSegura;
	}

	public void adicionaMensagens(Mensagem mensagem) {
		this.mensagens.add(mensagem);
	}

}
