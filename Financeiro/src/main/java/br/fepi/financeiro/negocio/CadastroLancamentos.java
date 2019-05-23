package br.fepi.financeiro.negocio;

import java.io.Serializable;
import java.util.Date;

import br.fepi.financeiro.model.Lancamento;
import br.fepi.financeiro.negocio.exception.NegocioException;
import br.fepi.financeiro.repository.Lancamentos;

/**
 * Classe que implementa RNs de Lancamentos
 * @author alunos
 *
 */
public class CadastroLancamentos implements Serializable {

	private static final long serialVersionUID = 1L;

	private Lancamentos lancamentos;
	
	
	
	public CadastroLancamentos(Lancamentos lancamentos) {
		this.lancamentos = lancamentos;
	}

	public Lancamentos getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(Lancamentos lancamentos) {
		this.lancamentos = lancamentos;
	}

	/**
	 * RN01: Não permite data de pagamento futura.
	 * @param lancamento
	 * @throws NegocioException
	 */
	public void salvar(Lancamento lancamento) throws NegocioException{
		if(lancamento.getDataPagamento() != null && lancamento.getDataPagamento().after(new Date())) { //Verifica se a data de pagamento é nula e posterior ao dia atual
			throw new NegocioException("Data de pagamento não pode ser futura!");
		}
		this.lancamentos.guardar(lancamento);
	}
	
	/**
	 * RN02: Não permite excluir lancamento pago.
	 * @param lancamento
	 * @throws NegocioException
	 */
	public void excluir(Lancamento lancamento) throws NegocioException {
		
		lancamento = this.lancamentos.porId(lancamento.getId());
		
		if(lancamento.getDataPagamento() != null) {
			throw new NegocioException("Não é possível remover lançamentos pagos!");
		}
		this.lancamentos.remover(lancamento);
	}
}
