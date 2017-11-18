package repository;

import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.entity.Problema;
import model.entity.Versao;

public class VersaoRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Versao guardar(Versao versao) {
		EntityTransaction trx = this.manager.getTransaction();
		trx.begin();

		try {
			versao = this.manager.merge(versao);
			trx.commit();
			return versao;
		} catch (Exception e) {
			return null;
		}
	}

	public List<Versao> buscarVersaoPai() {
		List<Versao> versao;

		EntityTransaction trx = manager.getTransaction();
		trx.begin();

		try {
			versao = manager.createQuery("from Versao where isVersaoPai = :isVersaoPai order by nome", Versao.class)
					.setParameter("isVersaoPai", true).getResultList();
			trx.commit();
		} catch (Exception e) {
			versao = null;
		}

		return versao;
	}
	
	public Versao buscarVersaoPai(Problema problema) {
		Versao versao;

		EntityTransaction trx = manager.getTransaction();
		trx.begin();

		try {
			versao = manager.createQuery("from Versao where isVersaoPai = :isVersaoPai and problema_id = :problemaId", Versao.class)
					.setParameter("isVersaoPai", true)
					.setParameter("problemaId", problema.getId())
					.getSingleResult();
			trx.commit();
		} catch (Exception e) {
			versao = null;
		}

		return versao;
	}

	public Versao buscarPorId(Integer id) {
		return manager.find(Versao.class, id);
	}
}
