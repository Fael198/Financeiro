package br.fepi.financeiro.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/** * 
 * @author lduarte
 * Provê uma instância de EntityManagerFactory compartilhada na aplicação.
 */
public class DataSource {
	
	private static EntityManagerFactory factory ;
	
	static { factory = Persistence.createEntityManagerFactory("FinanceiroPU"); }
	
	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
		
	}

}
