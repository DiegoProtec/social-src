package br.com.social.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.social.modelo.Usuario;

public class UsuarioDao {

	@PersistenceContext
	private EntityManager manager;
	private TypedQuery<Usuario> query;
	private List<Usuario> usuarios = new ArrayList<>();
	private Usuario usuario = null;

	public void salvar(Usuario usuario) {
		getManager().persist(usuario);
	}

	public Usuario buscaPorId(Integer id) {
		return manager.find(Usuario.class, id);
	}

	public Usuario consultaUsuario(Usuario usuario) {
		String jpql = "select u from Usuario u where u.email = :pEmail and u.senha = :pSenha";
		setQuery(getManager().createQuery(jpql, Usuario.class));
		getQuery().setParameter("pEmail", usuario.getEmail());
		getQuery().setParameter("pSenha", usuario.getSenha());
		try {
			setUsuario(getQuery().getSingleResult());
		} catch (NoResultException e) {
			return getUsuario();
		}
		return getUsuario();
	}

	public Usuario consultaUsuario(String email) {
		String jpql = "select u from Usuario u where u.email = :pEmail";
		setQuery(getManager().createQuery(jpql, Usuario.class));
		getQuery().setParameter("pEmail", email);
		try {
			setUsuario(getQuery().getSingleResult());
		} catch (NoResultException e) {
			return getUsuario();
		}
		return getUsuario();
	}

	public Usuario procuraEmail(Usuario usuario) {
		String jpql = "select u from Usuario u where u.email = :pEmail";
		setQuery(getManager().createQuery(jpql, Usuario.class));
		getQuery().setParameter("pEmail", usuario.getEmail());
		try {
			setUsuario(getQuery().getSingleResult());
		} catch (NoResultException e) {
			return getUsuario();
		}
		return getUsuario();
	}

	public void buscaContatos(Usuario usuario) {

	}

	private EntityManager getManager() {
		return manager;
	}

	private TypedQuery<Usuario> getQuery() {
		return query;
	}

	private void setQuery(TypedQuery<Usuario> query) {
		this.query = query;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}
