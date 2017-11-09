package service;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import model.entity.Instituicao;
import repository.InstituicaoRepository;
import util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Instituicao.class)
public class InstituicaoConverter implements Converter {

	private InstituicaoRepository repositorioInstituicao;

	public InstituicaoConverter() {
		repositorioInstituicao = CDIServiceLocator.getBean(InstituicaoRepository.class);
	}

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		Instituicao retorno = null;

		if (value != null) {
			Integer id = new Integer(value);
			retorno = repositorioInstituicao.buscarPorId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if (value != null) {
			if (((Instituicao) value).getId() == null) {
				return "";
			}

			return ((Instituicao) value).getId().toString();
		}

		return "";
	}

}
