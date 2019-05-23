package br.fepi.financeiro.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.fepi.financeiro.model.Lancamento;

public class Lancamentos implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private EntityManager em;

	public Lancamentos(EntityManager em) {
		this.em = em;
	}
	
	public List<Lancamento> todos() {
		TypedQuery<Lancamento> query = em.createQuery("from Lancamento", Lancamento.class);
		return query.getResultList();
	}
	
	public Lancamento porId (Long id) {
		return em.find(Lancamento.class, id);
	}
	
	public void adcionar(Lancamento lancamento){
		em.persist(lancamento);
	}

	/**
	 * Serve tanto para inserir quanto para atualizar objetos
	 * @param
	 * @return
	 */
	public void guardar (Lancamento lancamento) {
		em.merge(lancamento);
	}
	
	public void remover (Lancamento lancamento) {
		this.em.remove(lancamento);
	}
	
}
