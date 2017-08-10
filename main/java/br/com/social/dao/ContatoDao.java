package br.com.social.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.social.modelo.Amizade;

public class ContatoDao {

	@PersistenceContext
	private EntityManager manager;

	public void salvar(Amizade contato) {
		getManager().persist(contato);
	}

	private EntityManager getManager() {
		return manager;
	}

}
