package br.fepi.financeiro.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import br.fepi.financeiro.model.Lancamento;
import br.fepi.financeiro.repository.Lancamentos;
import br.fepi.financeiro.util.DataSource;

@ManagedBean
@ViewScoped
public class LancamentosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Lancamento> lancamentos;
	
	public void consultar() {
		EntityManager em = DataSource.getEntityManager();
		lancamentos = new Lancamentos(em).todos();
		em.close();
	}
	
	public void exluir() {}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}	

}
