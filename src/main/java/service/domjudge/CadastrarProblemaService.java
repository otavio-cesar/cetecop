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

	// TODO Implementar injecao automatica de unidade de persistencia
	private EntityManager manager;

	private EntityManagerFactory factory;

	public CadastrarProblemaService() {
		factory = Persistence.createEntityManagerFactory("domjudge");
	}

	public void guardar(Versao versao, Evento evento) {
		manager = factory.createEntityManager();

		inserirProblema(manager, versao, evento);

		manager.close();
	}

	// Insere um registo por vez, para remover conflito na insercao problemas em um
	// evento, pois O MAX(probid) utilizado, poderia retornar id's invalidos.
	private static synchronized void inserirProblema(EntityManager manager, Versao versao, Evento evento) {
		int problemaId;

		EntityTransaction trx = manager.getTransaction();

		trx.begin();
		manager.createNativeQuery("INSERT INTO problem (timelimit, name) VALUES (:timelimit, :name)")
				.setParameter("timelimit", 959).setParameter("name", "valdue").executeUpdate();
		trx.commit();

		trx.begin();
		problemaId = (int) manager.createNativeQuery("SELECT MAX(probid) FROM problem").getSingleResult();
		trx.commit();

		trx.begin();
		manager.createNativeQuery(
				"INSERT INTO contestproblem (cid, probid, shortname) VALUES (:cid, :probid, :shortname)")
				.setParameter("cid", 2).setParameter("probid", problemaId)
				.setParameter("shortname", "short" + problemaId).executeUpdate();
		trx.commit();

	}
}
