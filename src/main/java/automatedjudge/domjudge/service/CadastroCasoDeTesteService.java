package automatedjudge.domjudge.service;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import model.entity.CasoDeTeste;
import model.entity.mapper.CasoDeTesteMapper;
import repository.mapper.CasoDeTesteMapperRepository;
import repository.mapper.ProblemaMapperRepository;

public class CadastroCasoDeTesteService {

	private ProblemaMapperRepository problemaMapperRepository;

	public CadastroCasoDeTesteService() {
		problemaMapperRepository = new ProblemaMapperRepository();
	}

	public void guardar(List<CasoDeTeste> casosDeTeste) {
		ArrayList<CasoDeTesteMapper> casosDeTesteMapper = new ArrayList<>();
		Integer problemaIdExternal;
		
		EntityManager managerDomjudge = Persistence.createEntityManagerFactory("domjudge").createEntityManager();

		for (CasoDeTeste casoDeTeste : casosDeTeste) {
			problemaIdExternal = problemaMapperRepository.buscaProblemaIdExternal(casoDeTeste.getProblema().getId());
			casosDeTesteMapper.add(inserirCasosDeTeste(managerDomjudge, casoDeTeste, problemaIdExternal));
		}

		for (CasoDeTesteMapper casoDeTesteMapper : casosDeTesteMapper) {
			new Thread(new InsereCasoDeTesteMapper(casoDeTesteMapper)).start();
		}

		managerDomjudge.close();
	}

	// Insere registro por vez.
	private static synchronized CasoDeTesteMapper inserirCasosDeTeste(EntityManager manager, CasoDeTeste casoDeTeste, 
			Integer problemaIdExternal) {
		
		Integer casoDeTesteId;
		Integer rank;

		EntityTransaction trx = manager.getTransaction();

		trx.begin();
		
		rank = (Integer) manager.createNativeQuery("SELECT MAX(rank) FROM testcase where probid = :probid")
				.setParameter("probid", problemaIdExternal)
				.getSingleResult();

		if (rank == null) {
			rank = 1;
		} else {
			rank++;
		}
		
		manager.createNativeQuery("INSERT INTO testcase (probid, rank, description, input, output) VALUES (:probid, "
				+ ":rank, :description, :input, :output)")
				.setParameter("probid", problemaIdExternal)
				.setParameter("rank", rank)
				.setParameter("description", casoDeTeste.getDescricao().getBytes())
				.setParameter("input", casoDeTeste.getEntrada().trim().getBytes())
				.setParameter("output", casoDeTeste.getSaida().trim().getBytes())
				.executeUpdate();

		casoDeTesteId = (Integer) manager.createNativeQuery("SELECT MAX(testcaseid) FROM testcase").getSingleResult();
		
		trx.commit();

		return new CasoDeTesteMapper(casoDeTeste.getId(), casoDeTesteId, casoDeTeste.getProblema().getId());
	}

	private class InsereCasoDeTesteMapper implements Runnable {
		private CasoDeTesteMapperRepository casoDeTesteMapperRepository;
		private CasoDeTesteMapper casoDeTesteMapper;

		public InsereCasoDeTesteMapper(CasoDeTesteMapper casoDeTesteMapper) {
			this.casoDeTesteMapperRepository = new CasoDeTesteMapperRepository();
			this.casoDeTesteMapper = casoDeTesteMapper;
		}

		@Override
		public void run() {
			casoDeTesteMapperRepository.guardar(casoDeTesteMapper);
		}
	}
}
