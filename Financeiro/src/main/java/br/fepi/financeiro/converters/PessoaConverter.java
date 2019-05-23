package br.fepi.financeiro.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import br.fepi.financeiro.model.Pessoa;
import br.fepi.financeiro.repository.Pessoas;
import br.fepi.financeiro.util.DataSource;

@FacesConverter(forClass = Pessoa.class)
public class PessoaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Pessoa retorno = null;
		EntityManager em = DataSource.getEntityManager();
		try {
			if (arg2 != null && !"".equals(arg2)){
				retorno = new Pessoas(em).pessoaId(new Long(arg2));
			}
			return retorno;
		} finally {
			em.close();
		}
		
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 != null){
			Pessoa pessoa = ((Pessoa) arg2);
			return pessoa.getId() == null ? null : pessoa.getId().toString();
		}
		return null;
	}

}
