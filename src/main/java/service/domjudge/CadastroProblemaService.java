package service.domjudge;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import model.Evento;
import model.entity.ProblemaMapper;
import model.entity.Versao;
import repository.ProblemaMapperRepository;

public class CadastroProblemaService implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private static final int limiteMaximoExecucao = 9999;

	// TODO Implementar injecao automatica de unidade de persistencia
	private EntityManager managerDomjudge;
	
	private ProblemaMapperRepository problemaMapperRepository;

	private EntityManagerFactory factory;

	public CadastroProblemaService() {
		factory = Persistence.createEntityManagerFactory("domjudge");
		problemaMapperRepository = new ProblemaMapperRepository();
	}
	
	public void guardar(Versao versao, Evento evento) {
		int problemaId;
		managerDomjudge = factory.createEntityManager();

		problemaId = inserirProblema(managerDomjudge, versao, evento);

		ProblemaMapper problemaMapper = new ProblemaMapper(versao.getProblema().getId(), problemaId);
		problemaMapperRepository.guardar(problemaMapper);

		managerDomjudge.close();
	}

	// Insere um registo por vez, para remover o conflito na insercao problemas em
	// um
	// evento, pois o MAX(probid) utilizado poderia retornar id's invalidos.
	private static synchronized int inserirProblema(EntityManager manager, Versao versao, Evento evento) {
		int problemaId;

		EntityTransaction trx = manager.getTransaction();

		trx.begin();
		manager.createNativeQuery("INSERT INTO problem (timelimit, name) VALUES (:timelimit, :name)")
				.setParameter("timelimit", limiteMaximoExecucao).setParameter("name", versao.getNome()).executeUpdate();
		trx.commit();

		trx.begin();
		problemaId = (int) manager.createNativeQuery("SELECT MAX(probid) FROM problem").getSingleResult();
		trx.commit();

		trx.begin();
		manager.createNativeQuery(
				"INSERT INTO contestproblem (cid, probid, shortname) VALUES (:cid, :probid, :shortname)")
				.setParameter("cid", evento.getId()).setParameter("probid", problemaId)
				.setParameter("shortname", versao.getNome().replace(" ", "") + problemaId).executeUpdate();
		trx.commit();

		return problemaId;
	}

}
