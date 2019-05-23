package br.fepi.financeiro.util;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.fepi.financeiro.model.Lancamento;
import br.fepi.financeiro.model.Pessoa;
import br.fepi.financeiro.model.TipoLancamento;

public class CriarLancamentosApp {
	
	public static void main(String[] args) {
		EntityManager em = DataSource.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		
		Calendar dataVencimento1 = Calendar.getInstance();
		dataVencimento1.set(2013, 10, 1, 0, 0, 0);
		
		Calendar dataVencimento2 = Calendar.getInstance();
		dataVencimento2.set(2013, 12, 10, 0, 0, 0);
		
		Pessoa cliente = new Pessoa();
		cliente.setNome("Juca Chaves");
		Pessoa fornecedor = new Pessoa();
		fornecedor.setNome("Bar do Tião");
		
		Lancamento lancamento1 = new Lancamento();
		lancamento1.setDescricao("Venda de Pamonha");
		lancamento1.setPessoa(cliente);
		lancamento1.setDataVencimento(dataVencimento1.getTime());
		lancamento1.setDataPagamento(dataVencimento1.getTime());
		lancamento1.setValor(new BigDecimal(103_000));
		lancamento1.setTipo(TipoLancamento.RECEITA);
		
		Lancamento lancamento2 = new Lancamento();
		lancamento2.setDescricao("Venda de Pastel de Milho");
		lancamento2.setPessoa(cliente);
		lancamento2.setDataVencimento(dataVencimento1.getTime());
		lancamento2.setDataPagamento(dataVencimento1.getTime());
		lancamento2.setValor(new BigDecimal(15_000));
		lancamento2.setTipo(TipoLancamento.RECEITA);
		
		Lancamento lancamento3 = new Lancamento();
		lancamento3.setDescricao("Pinga do garrafão");
		lancamento3.setPessoa(fornecedor);
		lancamento3.setDataVencimento(dataVencimento2.getTime());		
		lancamento3.setValor(new BigDecimal(68_000));
		lancamento3.setTipo(TipoLancamento.DESPESA);
		
		em.persist(cliente);
		em.persist(fornecedor);
		em.persist(lancamento1);
		em.persist(lancamento2);
		em.persist(lancamento3);
		
		et.commit();
		em.close();
		
	}

}