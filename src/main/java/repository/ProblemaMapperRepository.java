package repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import model.entity.ProblemaMapper;

public class ProblemaMapperRepository {

	public void guardar(ProblemaMapper problemaMapper) {
		EntityManager manager = Persistence.createEntityManagerFactory("cetecop").createEntityManager();
		EntityTransaction trx = manager.getTransaction();

		trx.begin();

		try {
			manager.persist(problemaMapper);
			trx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		manager.close();
	}

	public Integer buscaProblemaIdExternal(Integer problemaIdCetecop) {
		Integer problemaIdExternal;

		EntityManager manager = Persistence.createEntityManagerFactory("cetecop").createEntityManager();
		EntityTransaction trx = manager.getTransaction();
			
		trx.begin();
		problemaIdExternal = (Integer) manager
				.createNativeQuery("SELECT problemaIdExternal FROM Problema_Mapper where problemaIdCetecop = :problemaIdCetecop")
				.setParameter("problemaIdCetecop", problemaIdCetecop)
				.getSingleResult();
		trx.commit();
		
		manager.close();
		
		return problemaIdExternal;
	}
}
