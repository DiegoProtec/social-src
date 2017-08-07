package br.com.social.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.social.modelo.Contato;

public class ContatoDao {

	@PersistenceContext
	private EntityManager manager;
	private String jpql;
	private TypedQuery<Contato> query = null;
	private List<Contato> contatos = null;

	public ContatoDao() {

	}

	public List<Contato> listaContatos() {
		this.jpql = "select c from Contato c";
		this.query = this.manager.createQuery(this.jpql, Contato.class);

		try {
			this.contatos = this.query.getResultList();
		} catch (NoResultException e) {
			return this.contatos;
		}
		return this.contatos;
	}

}
