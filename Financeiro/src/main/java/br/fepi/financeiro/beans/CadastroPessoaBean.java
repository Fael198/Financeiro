package br.fepi.financeiro.beans;

import java.io.Serializable;

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
public class CadastroPessoaBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
		
	private Pessoa pessoa;
	
	public void prepararCadastro() {
		if (this.pessoa == null) {
			this.pessoa = new Pessoa();
		}
	}
	
	public void salvar(){
		
		EntityManager em = DataSource.getEntityManager();
		EntityTransaction et = em.getTransaction();
		FacesContext faces = FacesContext.getCurrentInstance();
		
		try {
			et.begin();
			CadastroPessoas cadastro = new CadastroPessoas (new Pessoas(em));
			cadastro.salvar(pessoa);
			this.pessoa = new Pessoa();
			faces.addMessage(null, new FacesMessage("Salvo com sucesso."));
			et.commit();
		} catch (NegocioException e) {
			et.rollback();
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			faces.addMessage(null, mensagem);
		}finally{
			em.close();
		}
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}	

}
