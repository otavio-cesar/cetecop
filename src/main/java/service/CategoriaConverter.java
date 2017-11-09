package service;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import model.entity.Categoria;
import repository.CategoriaRepository;
import util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Categoria.class)
public class CategoriaConverter implements Converter {

	private CategoriaRepository repositorioCategoria;

	public CategoriaConverter() {
		repositorioCategoria = CDIServiceLocator.getBean(CategoriaRepository.class);
	}

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		Categoria retorno = null;

		if (value != null) {
			Integer id = new Integer(value);
			retorno = repositorioCategoria.buscarPorId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if (value != null) {
			if (((Categoria) value).getId() == null) {
				return "";
			}
			
			return ((Categoria) value).getId().toString();
		}
		
		return "";
	}

}
