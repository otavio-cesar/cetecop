package repository;

import java.io.Serializable;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.entity.Versao;

public class VersaoRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public boolean guardar(Versao versao) {
		EntityTransaction trx = this.manager.getTransaction();
		trx.begin();

		try {
			this.manager.merge(versao);
			trx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
