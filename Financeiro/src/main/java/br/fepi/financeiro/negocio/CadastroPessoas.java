package br.fepi.financeiro.negocio;

import java.io.Serializable;

import br.fepi.financeiro.model.Pessoa;
import br.fepi.financeiro.negocio.exception.NegocioException;
import br.fepi.financeiro.repository.Pessoas;

public class CadastroPessoas implements Serializable {

	private static final long serialVersionUID = 1L;
	private Pessoas pessoas;

	public CadastroPessoas(Pessoas pessoas) {
		this.pessoas = pessoas;
	}

	/**
	 * Método para salvar pessoas.
	 * @param pessoa
	 * @throws NegocioException
	 */
	public void salvar(Pessoa pessoa) throws NegocioException {
		this.pessoas.guardar(pessoa);
	}
	
	/**
	 * Método de exclusão
	 * @param pessoa
	 * @throws NegocioException
	 */
	public void excluir (Pessoa pessoa) throws NegocioException{
		pessoa = this.pessoas.pessoaId(pessoa.getId());
		this.pessoas.remover(pessoa);
	}

}
