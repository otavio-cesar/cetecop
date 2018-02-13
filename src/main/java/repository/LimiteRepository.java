package repository;

import java.io.Serializable;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.entity.Limite;
import util.jpa.EntityManagerProducer;

public class LimiteRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManager manager = EntityManagerProducer.getentityManagerCetecop();

	public boolean guardar(Limite limite) {
		EntityTransaction trx = this.manager.getTransaction();
		trx.begin();

		try {
			this.manager.persist(limite);
			trx.commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
