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
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_usuario", uniqueConstraints = @UniqueConstraint(columnNames = { "email_usuario" }))
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Long id;

	@Column(name = "email_usuario")
	private String email;

	@Column(name = "senha_usuario")
	private String senha;

	@Column(name = "nome_usuario")
	private String nome;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", targetEntity = Contato.class, fetch = FetchType.LAZY)
	private List<Contato> contatos;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "participante", targetEntity = ConversasParticipantes.class, fetch = FetchType.LAZY)
	private List<ConversasParticipantes> conversas;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "emissor", targetEntity = Mensagem.class, fetch = FetchType.LAZY)
	private List<Mensagem> mensagens;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Contato> getContatos() {
		List<Contato> listaSegura = Collections.unmodifiableList(this.contatos);
		return listaSegura;
	}

	public List<ConversasParticipantes> getConversas() {
		List<ConversasParticipantes> listaSegura = Collections.unmodifiableList(this.conversas);
		return listaSegura;
	}

	public List<Mensagem> getMensagens() {
		List<Mensagem> listaSegura = Collections.unmodifiableList(this.mensagens);
		return listaSegura;
	}

	public void adicionaContatos(Contato contato) {
		this.contatos.add(contato);
	}

	public void adicionaConversas(ConversasParticipantes conversa) {
		this.conversas.add(conversa);
	}

	public void adicionaMensagens(Mensagem mensagem) {
		this.mensagens.add(mensagem);
	}

}
