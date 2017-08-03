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

	/*
	 * todos os relacionamentos abaixo com as entidades contato e mensagem são
	 * bidirecionais por isso a entidade usuario precisa: realizar update cascade
	 * carregamento lazy adicionar listas das entidades fracas
	 */

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "emissor", targetEntity = Mensagem.class, fetch = FetchType.LAZY)
	private List<Mensagem> perguntas;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "receptor", targetEntity = Mensagem.class, fetch = FetchType.LAZY)
	private List<Mensagem> respostas;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "solicitante", targetEntity = Mensagem.class, fetch = FetchType.LAZY)
	private List<Contato> solicitantes;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "solicitado", targetEntity = Mensagem.class, fetch = FetchType.LAZY)
	private List<Contato> solicitados;

	public void adicionaEmissor(Mensagem mensagem) {
		this.perguntas.add(mensagem);
	}

	public void adicionaReceptor(Mensagem mensagem) {
		this.respostas.add(mensagem);
	}

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

	public List<Mensagem> getPerguntas() {
		List<Mensagem> listaSegura = Collections.unmodifiableList(this.perguntas);
		return listaSegura;
	}

	public List<Mensagem> getRespostas() {
		List<Mensagem> listaSegura = Collections.unmodifiableList(this.respostas);
		return listaSegura;
	}

	public List<Contato> getSolicitantes() {
		List<Contato> listaSegura = Collections.unmodifiableList(this.solicitantes);
		return listaSegura;
	}

	public List<Contato> getSolicitados() {
		List<Contato> listaSegura = Collections.unmodifiableList(this.solicitados);
		return listaSegura;
	}

}
