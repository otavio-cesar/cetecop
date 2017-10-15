package service;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import controller.PerfilUsuarioBean;
import model.Interesse;

@FacesConverter("interesse")
public class InteresseConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {

		System.out.println("ad" + value);
		if (value != null) {

			for (Interesse interesse : PerfilUsuarioBean.INTERESSES) {
				System.out.println(interesse);
				if (value.equals(interesse + "")) {
					System.out.println("abbd" + value);
					return interesse;
				}
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null && !value.equals("")) {
			Interesse interesse = (Interesse) value;
			return interesse.getNomeIcone();
		}
		return null;
	}

}