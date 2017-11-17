package repository;

import java.io.Serializable;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import model.entity.Solucao;

public class SolucaoRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Solucao guardar(Solucao solucao) {
		EntityTransaction trx = this.manager.getTransaction();
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

}
