package br.fepi.financeiro.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.fepi.financeiro.model.Pessoa;
import br.fepi.financeiro.negocio.CadastroPessoas;
import br.fepi.financeiro.negocio.exception.NegocioException;
import br.fepi.financeiro.repository.Pessoas;
import br.fepi.financeiro.util.DataSource;

@ManagedBean
@ViewScoped
public class PessoasBean implements Serializable {

	private static final long serialVersionUID = 1L;	

	private List<Pessoa> pessoas;
	
	private Pessoa pessoaSelecionada;

	/**
	 * Método que retorna lista de Pessoas.
	 */
	public void consultar() {
		EntityManager em = DataSource.getEntityManager();
		Pessoas pessoas = new Pessoas(em);
		this.pessoas = pessoas.todos();

		em.close();
	}
	
	public void excluir() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		EntityManager em = DataSource.getEntityManager();
		EntityTransaction et = em.getTransaction();
		
		CadastroPessoas cadastro = 
				new CadastroPessoas(new Pessoas(em));
		
		try {
			et.begin();
			cadastro.excluir(this.pessoaSelecionada);
			context.addMessage(null, 
					new FacesMessage("Pessoa excluída com sucesso."));
			et.commit();
			this.consultar();
		} catch (NegocioException e) {
			et.rollback();
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem); 
		}finally {
			em.close();
		}
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public Pessoa getPessoaSelecionada() {
		return pessoaSelecionada;
	}

	public void setPessoaSelecionada(Pessoa pessoaSelecionada) {
		this.pessoaSelecionada = pessoaSelecionada;
	}

}
