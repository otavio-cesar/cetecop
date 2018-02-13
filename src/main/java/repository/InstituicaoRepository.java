package repository;

import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.entity.Instituicao;
import util.jpa.EntityManagerProducer;

public class InstituicaoRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManager manager = EntityManagerProducer.getentityManagerCetecop();
	
	public List<Instituicao> buscarInstituicao() {
		return manager.createQuery("from Instituicao order by nome", Instituicao.class).getResultList();
	}

	public Instituicao buscarPorId(Integer id) {
		return manager.find(Instituicao.class, id);
	}

	public boolean guardar(Instituicao instituicao) {
		EntityTransaction trx = this.manager.getTransaction();
		trx.begin();

		try {
			this.manager.persist(instituicao);
			trx.commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
}
