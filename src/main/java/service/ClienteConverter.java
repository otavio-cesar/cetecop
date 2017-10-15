package service;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import repository.ClienteRepository;
import util.cdi.CDIServiceLocator;
import model.Cliente;

//@FacesConverter("cliente")
@FacesConverter(forClass = Cliente.class)
public class ClienteConverter implements Converter {

	// @Inject
	private ClienteRepository repositorioCliente;

	public ClienteConverter() {
		repositorioCliente = CDIServiceLocator.getBean(ClienteRepository.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Cliente retorno = null;

		if (value != null) {
			Long id = new Long(value);
			retorno = repositorioCliente.buscarPorId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			if (((Cliente) value).getId() == null) {
				// System.out.println("qbp " + value);
				return "";
			}
			return ((Cliente) value).getId().toString();
		}
		return "";
	}
}