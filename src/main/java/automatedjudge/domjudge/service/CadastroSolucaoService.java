package automatedjudge.domjudge.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.Evento;
import model.entity.Equipe;
import model.entity.Solucao;

public class CadastroSolucaoService {

	public void guardar(Solucao solucao, Evento evento, Equipe equipe) {
		EntityManager managerDomjudge = Persistence.createEntityManagerFactory("domjudge").createEntityManager();
		
		EntityTransaction trx = managerDomjudge.getTransaction();

		trx.begin();
		
		managerDomjudge.createNativeQuery("INSERT INTO submission (cid, teamid, probid, langid, submittime)"
				+ " VALUES (:cid, :teamid, :langid, :submittime)")
				.setParameter("cid", evento.getId())
				.setParameter("teamid", rank)
				.setParameter("langid", casoDeTeste.getDescricao().getBytes())
				.setParameter("submittime", casoDeTeste.getEntrada().getBytes())
				.executeUpdate();
		
		trx.commit();
	}

}
