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

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "conversa", targetEntity = ParticipanteConversa.class, fetch = FetchType.LAZY)
	private List<ParticipanteConversa> participantes;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<ParticipanteConversa> getParticipantes() {
		List<ParticipanteConversa> listaSegura = Collections.unmodifiableList(this.participantes);
		return listaSegura;
	}

	public void adicionaParticipantes(ParticipanteConversa participante) {
		this.participantes.add(participante);
	}

}
