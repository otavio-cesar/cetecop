package repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import model.entity.Equipe;

public class EquipeRepository{

	public Equipe guardar(Equipe equipe) {
		Equipe retorno;
		
		EntityManager manager = Persistence.createEntityManagerFactory("cetecop").createEntityManager();

		EntityTransaction trx = manager.getTransaction();

		trx.begin();

		try {
			retorno = manager.merge(equipe);
			trx.commit();
		} catch (Exception e) {
			retorno = null;
		}

		manager.close();
		
		return retorno;
	}

	public Equipe buscarPorId(Integer id) {
		Equipe equipe;
		
		EntityManager manager = Persistence.createEntityManagerFactory("cetecop").createEntityManager();

		equipe =  manager.find(Equipe.class, id);
		
		manager.close();
		
		return equipe;
	}

}
