package br.com.social.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.social.modelo.Usuario;

public class UsuarioDao {

	@PersistenceContext
	private EntityManager manager;
	private TypedQuery<Usuario> query;

	public void salvar(Usuario usuario) {
		manager.persist(usuario);
	}

	public Usuario alterarLista(Usuario usuario) {
		System.out.println("antes de merge");
		manager.merge(usuario);
		System.out.println("depois de merge");
		String jpql = "select distinct(u) from Usuario u "
				+ "join fetch u.contatos";
		query = manager.createQuery(jpql, Usuario.class);
		return query.getSingleResult();
	}

	public Usuario consultarUsuario(Long id) {
		return manager.find(Usuario.class, id);
	}

	public Usuario consultarUsuario(String email) {
		String jpql = "select distinct(u) from Usuario u"
				+ " left join u.contatos"
				+ " where u.email = :pEmail";
		query = manager.createQuery(jpql, Usuario.class);
		query.setParameter("pEmail", email);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Usuario consultarUsuario(Usuario usuario) {
		String jpql = "select distinct(u) from Usuario u"
				+ " left join u.contatos"
				+ " where u.email = :pEmail and u.senha = :pSenha";
		query = manager.createQuery(jpql, Usuario.class);
		query.setParameter("pEmail", usuario.getEmail());
		query.setParameter("pSenha", usuario.getSenha());
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
