package repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.Instituicao;

public class InstituicaoRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public List<Instituicao> buscarInstituicao() {
		return manager.createQuery("from Instituicao order by nome", Instituicao.class).getResultList();
	}

	public Instituicao buscarPorId(Integer id) {
		return manager.find(Instituicao.class, id);
	}

	public Instituicao guardar(Instituicao instituicao) {
		Instituicao retornoInstituicao = null;
		
		EntityTransaction trx = this.manager.getTransaction();
		trx.begin();

		try {
			retornoInstituicao = this.manager.merge(instituicao);
			trx.commit();
			return retornoInstituicao;
		} catch (Exception e) {
			return retornoInstituicao;
		}
	}
}
