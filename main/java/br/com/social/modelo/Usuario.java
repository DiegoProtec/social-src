package br.com.social.modelo;

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

	@OneToMany(mappedBy = "emissor", targetEntity = Mensagem.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Mensagem> perguntas;

	@OneToMany(mappedBy = "receptor", targetEntity = Mensagem.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Mensagem> respostas;

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
		return perguntas;
	}

	public void setPerguntas(List<Mensagem> perguntas) {
		this.perguntas = perguntas;
	}

	public List<Mensagem> getRespostas() {
		return respostas;
	}

	public void setRespostas(List<Mensagem> respostas) {
		this.respostas = respostas;
	}

}
