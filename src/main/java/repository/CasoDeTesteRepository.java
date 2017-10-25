package repository;

import java.io.Serializable;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import model.CasoDeTeste;

public class CasoDeTesteRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public boolean guardar(CasoDeTeste casoDeTeste) {
		EntityTransaction trx = this.manager.getTransaction();
		trx.begin();

		try {
			this.manager.persist(casoDeTeste);
			trx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// public List<Categoria> buscarCategoria() {
	// return manager.createQuery("from Categoria order by nome",
	// Categoria.class).getResultList();
	// }

	public CasoDeTeste buscarPorId(Integer id) {
		return manager.find(CasoDeTeste.class, id);
	}

}
