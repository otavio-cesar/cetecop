package repository;

import java.io.Serializable;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import model.Limite;

public class LimiteRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

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
