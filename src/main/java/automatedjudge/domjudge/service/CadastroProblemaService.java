package automatedjudge.domjudge.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import model.Evento;
import model.entity.Versao;
import model.entity.mapper.ProblemaMapper;
import repository.mapper.ProblemaMapperRepository;

public class CadastroProblemaService {

	private static final int limiteMaximoExecucao = 9999;

	private ProblemaMapperRepository problemaMapperRepository;

	public CadastroProblemaService() {
		problemaMapperRepository = new ProblemaMapperRepository();
	}

	public void guardar(Versao versao, Evento evento) {
		Integer problemaId;
		
		EntityManager managerDomjudge = Persistence.createEntityManagerFactory("domjudge").createEntityManager();
		
		problemaId = inserirProblema(managerDomjudge, versao, evento);

		ProblemaMapper problemaMapper = new ProblemaMapper(versao.getProblema().getId(), problemaId);
		problemaMapperRepository.guardar(problemaMapper);

		managerDomjudge.close();
	}

	// Insere um registro por vez.
	private static synchronized Integer inserirProblema(EntityManager manager, Versao versao, Evento evento) {
		Integer problemaId;

		EntityTransaction trx = manager.getTransaction();

		trx.begin();
		
		manager.createNativeQuery("INSERT INTO problem (timelimit, name) VALUES (:timelimit, :name)")
				.setParameter("timelimit", limiteMaximoExecucao)
				.setParameter("name", versao.getNome())
				.executeUpdate();

		problemaId = (Integer) manager.createNativeQuery("SELECT MAX(probid) FROM problem")
				.getSingleResult();

		manager.createNativeQuery(
				"INSERT INTO contestproblem (cid, probid, shortname) VALUES (:cid, :probid, :shortname)")
				.setParameter("cid", evento.getId())
				.setParameter("probid", problemaId)
				.setParameter("shortname", versao.getNome().replace(" ", "") + problemaId)
				.executeUpdate();
		
		trx.commit();

		return problemaId;
	}
	
}
