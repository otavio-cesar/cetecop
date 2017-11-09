package service.domjudge;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import model.entity.CasoDeTeste;

public class CadastroCasoDeTesteService {

	// TODO Implementar injecao automatica de unidade de persistencia
	private EntityManager managerDomjudge;

	private EntityManagerFactory factory;

	public CadastroCasoDeTesteService() {
		factory = Persistence.createEntityManagerFactory("domjudge");
	}

	public void guardar(List<CasoDeTeste> casosDeTeste) {
		managerDomjudge = factory.createEntityManager();

		inserirCasosDeTeste(managerDomjudge, casosDeTeste);

		managerDomjudge.close();
	}

	private void inserirCasosDeTeste(EntityManager manager, List<CasoDeTeste> casosDeTeste) {
		for (CasoDeTeste casoDeTeste : casosDeTeste) {
			Thread thread = new InsereThread(manager.getTransaction(), casoDeTeste);
			thread.start();
		}
	}

	private class InsereThread extends Thread {
		public CasoDeTeste casoDeTeste;
		public EntityTransaction trx;

		public InsereThread(EntityTransaction trx, CasoDeTeste casoDeTeste) {
			this.casoDeTeste = casoDeTeste;
			this.trx = trx;
		}

		@Override
		public void run() {
			trx.begin();
			managerDomjudge.createNativeQuery("INSERT INTO testcase (probid, rank) VALUES (:probid, :rank)")
					.setParameter("probid", casoDeTeste.getProblema().getId())
					.setParameter("rank", -1)
					.executeUpdate();
			trx.commit();
		}
	}

}
