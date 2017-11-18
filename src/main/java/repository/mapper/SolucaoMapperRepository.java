package repository.mapper;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import model.entity.mapper.SolucaoMapper;

public class SolucaoMapperRepository {

	public SolucaoMapper guardar(SolucaoMapper solucaoMapper) {
		EntityManager manager = Persistence.createEntityManagerFactory("cetecop").createEntityManager();
		EntityTransaction trx = manager.getTransaction();

		trx.begin();

		try {
			solucaoMapper = manager.merge(solucaoMapper);
			trx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		manager.close();
		
		return solucaoMapper;
	}

	// Do not depend this method on automatic injection
	public String buscaResultadoSolucao(SolucaoMapper solucaoMapper) {
		String resultado;
		
		EntityManager manager = Persistence.createEntityManagerFactory("domjudge").createEntityManager();
		
		EntityTransaction trx = manager.getTransaction();
		
		trx.begin();
		
		resultado = (String) manager.createNativeQuery("SELECT result FROM judging where submitid = :submitid")
				.setParameter("submitid", solucaoMapper.getProblemaIdExternal())
				.getSingleResult();
		
		trx.commit();
		
		manager.close();
		
		return resultado;
	}

	// Do not depend this method on automatic injection
	public void excluirMapper(SolucaoMapper solucaoMapper) {
		EntityManager manager = Persistence.createEntityManagerFactory("cetecop").createEntityManager();
		
		EntityTransaction trx = manager.getTransaction();

		trx.begin();
		
		manager.remove(solucaoMapper);
		 
		trx.commit();

		manager.close();
	}

	// TODO
	public Integer buscaProblemaIdExternal(Integer problemaIdCetecop) {
		Integer problemaIdExternal;

		EntityManager manager = Persistence.createEntityManagerFactory("cetecop").createEntityManager();
		EntityTransaction trx = manager.getTransaction();

		trx.begin();
		problemaIdExternal = (Integer) manager
				.createNativeQuery(
						"SELECT problemaIdExternal FROM Problema_Mapper where problemaIdSource = :problemaIdSource")
				.setParameter("problemaIdSource", problemaIdCetecop).getSingleResult();
		trx.commit();

		manager.close();

		return problemaIdExternal;
	}

}
