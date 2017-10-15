package repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import model.Cliente;

public class ClienteRepository implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public List<Cliente> buscarCliente() {
		return manager.createQuery("from Cliente", Cliente.class)
				.getResultList();

	}

	public Cliente buscarPorId(Long id) {
		return manager.find(Cliente.class, id);
	}
}
