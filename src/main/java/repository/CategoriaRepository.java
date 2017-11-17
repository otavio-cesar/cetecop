package repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.entity.Categoria;
import model.entity.Problema;
import model.entity.ProblemaHasCategoria;

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

	public List<Categoria> buscarCategorias(Problema problema) {
		List<ProblemaHasCategoria> problemaHasCategorias;
		List<Categoria> categorias = new ArrayList<Categoria>();

		EntityTransaction trx = manager.getTransaction();
		trx.begin();

		try {
			problemaHasCategorias = manager.createQuery("from ProblemaHasCategoria where problema_id = :problemaId"
					,ProblemaHasCategoria.class)
					.setParameter("problemaId", problema.getId())
					.getResultList();
			for (ProblemaHasCategoria problemaHasCategoria : problemaHasCategorias) {
				categorias.add(problemaHasCategoria.getCategoria());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			trx.commit();
		}

		return categorias;
	}
}
