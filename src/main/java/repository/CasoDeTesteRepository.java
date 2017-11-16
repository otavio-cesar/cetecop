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

	public CasoDeTeste guardar(CasoDeTeste casoDeTeste) {
		EntityTransaction trx = this.manager.getTransaction();
		trx.begin();

		try {
			casoDeTeste = manager.merge(casoDeTeste);
			trx.commit();
			return casoDeTeste;
		} catch (Exception e) {
			return null;
		}
	}

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
