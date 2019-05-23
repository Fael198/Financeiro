package br.fepi.financeiro.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.fepi.financeiro.model.Lancamento;
import br.fepi.financeiro.model.Pessoa;

public class Pessoas implements Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManager em;

	public Pessoas(EntityManager em) {
		this.em = em;
	}
	
	public Pessoa pessoaId(Long id){
		return em.find(Pessoa.class, id );
	}
	
	public List<Pessoa> todos(){
		TypedQuery<Pessoa> query = em.createQuery("from Pessoa p order by p.id", Pessoa.class);
		return query.getResultList();
	}
	
	public void adicionar (Pessoa pessoa){
		this.em.persist(pessoa);
	}
	
	/**
	 * Faz insert ou update
	 * @param pessoa
	 * @return
	 */
	public void guardar (Pessoa pessoa) {
		 this.em.merge(pessoa);
	}
	
	/**
	 * Remove pessoa
	 * @param pessoa
	 */
	public void remover(Pessoa pessoa) {
		this.em.remove(pessoa);
	}
		
}
