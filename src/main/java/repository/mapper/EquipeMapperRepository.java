package repository.mapper;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import model.entity.mapper.EquipeMapper;

public class EquipeMapperRepository {

	public void guardar(EquipeMapper equipeMapper) {
		EntityManager manager = Persistence.createEntityManagerFactory("cetecop").createEntityManager();
		
		EntityTransaction trx = manager.getTransaction();

		trx.begin();

		try {
			manager.persist(equipeMapper);
			trx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		manager.close();
	}

	public Integer buscaEquipeIdExternal(Integer equipeIdSource) {
		Integer problemaIdExternal;

		EntityManager manager = Persistence.createEntityManagerFactory("cetecop").createEntityManager();
		
		EntityTransaction trx = manager.getTransaction();

		trx.begin();
		
		problemaIdExternal = (Integer) manager
				.createNativeQuery(
						"SELECT equipeIdExternal FROM Equipe_Mapper where equipeIdSource = :equipeIdSource")
				.setParameter("equipeIdSource", equipeIdSource).getSingleResult();
		
		trx.commit();

		manager.close();

		return problemaIdExternal;
	}
}
