package controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import repository.ClienteRepository;
import model.Cliente;

@Named
@ViewScoped
// @RequestScoped
// @SessionScoped
public class CadastroClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ClienteRepository clienteReposiroty;
	// partialSubmit="true" process="bt cliente" update="cliente saida"
	private Cliente cliente;
	private String id2;

	public String getId2() {
		return id2;
	}

	public void setId2(String id2) {
		this.id2 = id2;
	}

	private List<Cliente> clientes;

	public CadastroClienteBean() {
		cliente = new Cliente();
	}

	public void inicializar(ComponentSystemEvent e) {
		System.out.println("inicializando");
		if (FacesContext.getCurrentInstance().isPostback()) {
		} else
			this.clientes = clienteReposiroty.buscarCliente();
	}

	public void salvar() {
		id2 = cliente.getId().toString();
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_FATAL, "fatal",
						"fatality"));
		clientes=null;
	}

	@NotNull
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
		return this.clientes;
	}

}