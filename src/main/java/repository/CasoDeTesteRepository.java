package repository;

import java.io.Serializable;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.entity.CasoDeTeste;

public class CasoDeTesteRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public boolean guardar(CasoDeTeste casoDeTeste) {
		EntityTransaction trx = this.manager.getTransaction();
		trx.begin();

		try {
			this.manager.merge(casoDeTeste);
			trx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// TODO Buscar por problema ID
	
	// public List<CasoDeTeste> buscarCasoDeTeste() {
	// return manager.createQuery("from CasoDeTeste",
	// CasoDeTeste.class).getResultList();
	// }

	public CasoDeTeste buscarPorId(Integer id) {
		return manager.find(CasoDeTeste.class, id);
	}

	public boolean excluir(CasoDeTeste casoDeTeste) {
		EntityTransaction trx = this.manager.getTransaction();
		trx.begin();

		try {
			System.out.println(casoDeTeste.getId());
			manager.remove(casoDeTeste);
			trx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
