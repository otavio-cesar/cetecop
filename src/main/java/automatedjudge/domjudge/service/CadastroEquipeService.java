package automatedjudge.domjudge.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import model.entity.Equipe;
import model.entity.mapper.EquipeMapper;
import repository.mapper.EquipeMapperRepository;

public class CadastroEquipeService {
	
	private static final int CATEGORYID_PARTICIPANTS = 3;
	
	private EquipeMapperRepository equipeMapperRepository;

	public CadastroEquipeService() {
		equipeMapperRepository = new EquipeMapperRepository();
	}

	public void guardar(Equipe equipe) {
		Integer equipeId;
		
		EntityManager managerDomjudge = Persistence.createEntityManagerFactory("domjudge").createEntityManager();
		
		equipeId = inserirEquipe(managerDomjudge, equipe);

		EquipeMapper problemaMapper = new EquipeMapper(equipe.getId(), equipeId);
		equipeMapperRepository.guardar(problemaMapper);

		managerDomjudge.close();
	}

	// Insere um registro por vez.
	private static synchronized Integer inserirEquipe(EntityManager manager, Equipe equipe) {
		Integer equipeId;

		EntityTransaction trx = manager.getTransaction();

		trx.begin();
		
		manager.createNativeQuery("INSERT INTO team (name, externalid, categoryid) VALUES (:name, :externalid, :categoryid)")
				.setParameter("name", equipe.getNome())
				.setParameter("externalid", equipe.getId())
				.setParameter("categoryid", CATEGORYID_PARTICIPANTS)
				.executeUpdate();

		equipeId = (Integer) manager.createNativeQuery("SELECT MAX(teamid) FROM team")
				.getSingleResult();
		
		trx.commit();

		return equipeId;
	}
	
}
