package service;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.entity.Versao;
import repository.VersaoRepository;
import util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Versao.class)
public class VersaoConverter implements Converter {

	private VersaoRepository repositorioVersao;

	public VersaoConverter() {
		repositorioVersao = CDIServiceLocator.getBean(VersaoRepository.class);
	}

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		Versao retorno = null;

		if (value != null) {
			Integer id = new Integer(value);
			retorno = repositorioVersao.buscarPorId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if (value != null) {
			if (((Versao) value).getId() == null) {
				return "";
			}

			return ((Versao) value).getId().toString();
		}

		return "";
	}

}
