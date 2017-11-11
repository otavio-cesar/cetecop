package automatedjudge.domjudge.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import model.Evento;
import model.entity.ProblemaMapper;
import model.entity.Versao;
import repository.ProblemaMapperRepository;

public class CadastroProblemaService {

	private static final int limiteMaximoExecucao = 9999;

	// TODO Injecao automatica de persistencia
	private EntityManager managerDomjudge;

	private ProblemaMapperRepository problemaMapperRepository;

	public CadastroProblemaService() {
		managerDomjudge = Persistence.createEntityManagerFactory("domjudge").createEntityManager();
		problemaMapperRepository = new ProblemaMapperRepository();
	}

	public void guardar(Versao versao, Evento evento) {
		int problemaId;

		problemaId = inserirProblema(managerDomjudge, versao, evento);

		ProblemaMapper problemaMapper = new ProblemaMapper(versao.getProblema().getId(), problemaId);
		problemaMapperRepository.guardar(problemaMapper);

		managerDomjudge.close();
	}

	// Permite a insercao de apenas um problema por vez no banco de dados.
	private static synchronized int inserirProblema(EntityManager manager, Versao versao, Evento evento) {
		int problemaId;

		EntityTransaction trx = manager.getTransaction();

		trx.begin();
		manager.createNativeQuery("INSERT INTO problem (timelimit, name) VALUES (:timelimit, :name)")
				.setParameter("timelimit", limiteMaximoExecucao)
				.setParameter("name", versao.getNome())
				.executeUpdate();

		problemaId = (int) manager.createNativeQuery("SELECT MAX(probid) FROM problem")
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
