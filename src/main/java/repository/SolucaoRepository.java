package repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import model.entity.Solucao;
import util.jpa.EntityManagerProducer;
public class SolucaoRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManager manager = EntityManagerProducer.getentityManagerCetecop();;

	public Solucao guardar(Solucao solucao) {
		EntityTransaction trx = manager.getTransaction();

		trx.begin();

		try {
			solucao = manager.merge(solucao);
			trx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			solucao = null;
		}

		return solucao;
	}

	public List<Solucao> solucoesCorrigidas() {
		List<Solucao> solucoes = new ArrayList<Solucao>();

		EntityTransaction trx = manager.getTransaction();

		trx.begin();

		try {
			solucoes = manager.createQuery("from Solucao", Solucao.class).getResultList();
			trx.commit();
		} catch (Exception e) {

		}

		return solucoes;
	}
	
	// Do not depend this method on automatic injection
	public Solucao buscarPorId(Integer id) {
		Solucao solucao;

		EntityManager manager = EntityManagerProducer.getentityManagerCetecop();

		solucao = manager.find(Solucao.class, id);

		//manager.close();

		return solucao;
	}

	// Do not depend this method on automatic injection
	public void atualizaSolucao(Solucao solucao) {
		EntityManager manager = EntityManagerProducer.getentityManagerCetecop();

		EntityTransaction trx = manager.getTransaction();

		trx.begin();
		
		try {
			solucao = manager.merge(solucao);
			trx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		//manager.close();
	}
}
