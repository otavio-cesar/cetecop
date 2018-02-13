package repository;
import util.jpa.EntityManagerProducer;
import java.io.Serializable;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.entity.Problema;

public class ProblemaRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManager manager = EntityManagerProducer.getentityManagerCetecop();

	public Problema guardar(Problema problema) {
		EntityTransaction trx = this.manager.getTransaction();
		trx.begin();

		try {
			problema = this.manager.merge(problema);
			trx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return problema;
	}
}
