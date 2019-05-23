package br.fepi.financeiro.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.fepi.financeiro.model.Lancamento;
import br.fepi.financeiro.model.Pessoa;
import br.fepi.financeiro.model.TipoLancamento;
import br.fepi.financeiro.negocio.CadastroLancamentos;
import br.fepi.financeiro.negocio.exception.NegocioException;
import br.fepi.financeiro.repository.Lancamentos;
import br.fepi.financeiro.repository.Pessoas;
import br.fepi.financeiro.util.DataSource;

@ManagedBean
@ViewScoped
public class CadastroLancamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Lancamento lancamento;
	
	private List<Pessoa> todasPessoas = new ArrayList<>();
	
	public void prepararCadastro() {
		
		EntityManager em = DataSource.getEntityManager();
		try {
			this.todasPessoas = new Pessoas(em).todos();
		} finally {
			em.close();
		}
		
		if(this.lancamento == null) {
			this.lancamento = new Lancamento();
		}
	}
	
	public void salvar() {
		EntityManager em = DataSource.getEntityManager();
		EntityTransaction et = em.getTransaction();
		FacesContext faces = FacesContext.getCurrentInstance();
		
		try {
			et.begin();
			CadastroLancamentos cadastro = new CadastroLancamentos(new Lancamentos(em));
			cadastro.salvar(lancamento);
			this.lancamento = new Lancamento();
			faces.addMessage(null, new FacesMessage("Salvo com sucesso!"));
			et.commit();
		} catch (NegocioException e) {
			et.rollback();
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			faces.addMessage(null, mensagem);
		}finally {
			em.close();
		}
	}

	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}

	public List<Pessoa> getTodasPessoas() {
		return todasPessoas;
	}

	public void setTodasPessoas(List<Pessoa> todasPessoas) {
		this.todasPessoas = todasPessoas;
	}
	
	public TipoLancamento[] getTipoLancamento() {
		return TipoLancamento.values();
	}
	
	
	
}
