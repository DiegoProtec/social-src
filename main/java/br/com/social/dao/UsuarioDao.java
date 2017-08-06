package br.com.social.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.social.modelo.Usuario;

public class UsuarioDao {

	@PersistenceContext
	private EntityManager manager;
	private Usuario usuario = null;
	private String jpql = "";
	private TypedQuery<Usuario> query = null;

	public void salvar(Usuario usuario) {
		this.manager.persist(usuario);
	}

	public Usuario consultaUsuario(Usuario usuario) {
		this.jpql = "select u from Usuario u where u.email = :pEmail and u.senha = :pSenha";
		this.query = this.manager.createQuery(this.jpql, Usuario.class);
		this.query.setParameter("pEmail", usuario.getEmail());
		this.query.setParameter("pSenha", usuario.getSenha());

		try {
			this.usuario = this.query.getSingleResult();
		} catch (NoResultException e) {
			return this.usuario;
		}
		return this.usuario;
	}

	public Usuario consultaUsuario(String email) {
		this.jpql = "select u from Usuario u where u.email = :pEmail";
		this.query = this.manager.createQuery(this.jpql, Usuario.class);
		this.query.setParameter("pEmail", email);

		try {
			this.usuario = this.query.getSingleResult();
		} catch (NoResultException e) {
			return this.usuario;
		}
		return this.usuario;
	}
}
