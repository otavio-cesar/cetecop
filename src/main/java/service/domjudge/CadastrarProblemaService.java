package service.domjudge;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import model.Evento;
import model.entidades.Versao;

@Named
public class CadastrarProblemaService {

	// TODO Implementar injecao automatica de unidade de persistencia domjudge
	private EntityManager manager;

	public void guardar(Versao versao, Evento evento) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("domjudge");
		manager = factory.createEntityManager();

		EntityTransaction trx = this.manager.getTransaction();
		trx.begin();

		manager.createNativeQuery("INSERT INTO problem (timelimit, name) VALUES (:limit, :name)")
				.setParameter("limit", 959).setParameter("name", "valdue").executeUpdate();

		trx.commit();

		manager.close();

	}
}
