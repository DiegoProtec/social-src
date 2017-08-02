package br.com.social.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.social.modelo.Usuario;

public class UsuarioDao {
	
	@PersistenceContext
	EntityManager manager;
	
	public void salvar(Usuario usuario) {
		manager.persist(usuario);
	}
}
