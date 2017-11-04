package repository;

import java.io.Serializable;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import model.Problema;

public class ProblemaRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

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
