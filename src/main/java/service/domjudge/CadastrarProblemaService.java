package service.domjudge;

import java.io.Serializable;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Evento;
import model.entidades.Versao;

public class CadastrarProblemaService implements Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManager manager;

	public boolean guardar(Versao versao, Evento evento) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("PedidoPU");
		manager = factory.createEntityManager();

		manager.createQuery("INSERT INTO Problem () :custName").setParameter("custName", name)
				.setMaxResults(10).getResultList();
		return false;
	}
}
