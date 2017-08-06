package br.com.social.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.social.modelo.Usuario;

public class UsuarioDao {

	@PersistenceContext
	EntityManager manager;
	private Usuario usuario = null;

	public void salvar(Usuario usuario) {
		manager.persist(usuario);
	}

	public Usuario consultaUsuario(Usuario usuario) {
		String jpql = "select u from Usuario u where u.email = :pEmail and u.senha = :pSenha";

		TypedQuery<Usuario> query = manager.createQuery(jpql, Usuario.class);
		query.setParameter("pEmail", usuario.getEmail());
		query.setParameter("pSenha", usuario.getSenha());

		try {
			this.usuario = query.getSingleResult();
		} catch (NoResultException e) {
			return this.usuario;
		}
		return this.usuario;
	}

	public Usuario consultaUsuario(String email) {
		String jpql = "select u from Usuario u where u.email = :pEmail";

		TypedQuery<Usuario> query = manager.createQuery(jpql, Usuario.class);
		query.setParameter("pEmail", email);

		try {
			this.usuario = query.getSingleResult();
		} catch (NoResultException e) {
			return this.usuario;
		}
		return this.usuario;
	}
}
