package repository;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import model.entity.ProblemaMapper;

public class ProblemaMapperRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManager manager;

	public ProblemaMapperRepository() {
		manager = Persistence.createEntityManagerFactory("cetecop").createEntityManager();
	}

	public void guardar(ProblemaMapper problemaMapper) {
		EntityTransaction trx = manager.getTransaction();
		trx.begin();

		try {
			manager.persist(problemaMapper);
			trx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
