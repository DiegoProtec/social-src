package br.com.social.modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_usuario", uniqueConstraints = @UniqueConstraint(columnNames = { "email" }))
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String email;

	private String senha;

	private String nome;

	@ManyToMany
	@JoinTable(name = "rl_amizade", joinColumns = {
			@JoinColumn(name = "id_usuario", referencedColumnName = "id", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "id_contato", referencedColumnName = "id", nullable = false) })
	private List<Usuario> contatos;

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

	public List<Usuario> getContatos() {
		return contatos;
	}

	public void setContatos(Usuario usuario) {
		this.contatos.add(usuario);
	}

}
