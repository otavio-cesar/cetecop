package automatedjudge.domjudge.service;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import model.Evento;
import model.entity.Equipe;
import model.entity.Solucao;
import repository.mapper.EquipeMapperRepository;
import repository.mapper.ProblemaMapperRepository;

public class CadastroSolucaoService {

	private EquipeMapperRepository equipeMapperRepository;
	
	private ProblemaMapperRepository problemaMapperRepository;

	public CadastroSolucaoService() {
		equipeMapperRepository = new EquipeMapperRepository();
		problemaMapperRepository = new ProblemaMapperRepository();
	}

	public void guardar(Solucao solucao, Evento evento, Equipe equipe) {
		Integer solucaoId;
		
		Integer problemaId = problemaMapperRepository.buscaProblemaIdExternal(solucao.getProblema().getId());
		
		Integer equipeId = equipeMapperRepository.buscaEquipeIdExternal(equipe.getId());
		
		EntityManager managerDomjudge = Persistence.createEntityManagerFactory("domjudge").createEntityManager();

		solucaoId = inserirEquipe(managerDomjudge, solucao, evento, equipeId, problemaId);
		
		inserirArquivoSolucao(managerDomjudge, solucao, solucaoId);
		
		// TODO mapper
	}

	private void inserirArquivoSolucao(EntityManager manager, Solucao solucao, Integer solucaoId) {
		EntityTransaction trx = manager.getTransaction();

		trx.begin();
		
		manager.createNativeQuery(
				"INSERT INTO submission_file (submitid, sourcecode, filename, rank) VALUES (:submitid, :sourcecode, "
				+ ":filename, :rank)")
				.setParameter("submitid", solucaoId)
				.setParameter("sourcecode", solucao.getCodigoFonte().getBytes())
				.setParameter("filename", solucao.getNomeArquivo())
				.setParameter("rank", 0)
				.executeUpdate();
		
		trx.commit();
	}

	// Insere uma submissao por vez.
	private static synchronized Integer inserirEquipe(EntityManager manager, Solucao solucao, Evento evento,
			Integer equipeId, Integer problemaId) {
		Integer solucaoId;

		EntityTransaction trx = manager.getTransaction();

		trx.begin();

		manager
				.createNativeQuery("INSERT INTO submission (cid, teamid, probid, langid, submittime)"
						+ " VALUES (:cid, :teamid, :probid, :langid, :submittime)")
				.setParameter("cid", evento.getId())
				.setParameter("teamid", equipeId)
				.setParameter("probid", problemaId)
				.setParameter("langid", solucao.getLinguagem().toString())
				.setParameter("submittime", new Date().getTime())
				.executeUpdate();

		solucaoId = (Integer) manager.createNativeQuery("SELECT MAX(submitid) FROM submission")
				.getSingleResult();

		trx.commit();

		return solucaoId;
	}

}
