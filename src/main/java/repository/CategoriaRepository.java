package repository;

import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.entidades.Categoria;

public class CategoriaRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public boolean guardar(Categoria categoria) {
		EntityTransaction trx = this.manager.getTransaction();
		trx.begin();

		try {
			this.manager.persist(categoria);
			trx.commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public List<Categoria> buscarCategoria() {
		return manager.createQuery("from Categoria order by nome", Categoria.class).getResultList();
	}

	public Categoria buscarPorId(Integer id) {
		return manager.find(Categoria.class, id);
	}

}
