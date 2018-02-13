package repository.mapper;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.entity.mapper.CasoDeTesteMapper;
import util.jpa.EntityManagerProducer;
public class CasoDeTesteMapperRepository{

	public void guardar(CasoDeTesteMapper casoDeTesteMapper) {
		EntityManager manager = EntityManagerProducer.getentityManagerCetecop();
		EntityTransaction trx = manager.getTransaction();
		
		trx.begin();

		try {
			manager.persist(casoDeTesteMapper);
			trx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//manager.close();
	}
}
