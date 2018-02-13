package repository;
import util.jpa.EntityManagerProducer;
import java.io.Serializable;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.entity.ProblemaHasCategoria;

public class ProblemaHasCategoriaRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManager manager = EntityManagerProducer.getentityManagerCetecop();

	public boolean guardar(ProblemaHasCategoria problemaCategoria) {
		EntityTransaction trx = this.manager.getTransaction();
		trx.begin();

		try {
			this.manager.merge(problemaCategoria);
			trx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
