package repository;

import java.io.Serializable;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import model.entity.UsuarioHasEquipe;

public class UsuarioHasEquipeRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	public boolean guardar(UsuarioHasEquipe usuarioHasEquipe) {
		EntityTransaction trx = manager.getTransaction();
		
		trx.begin();

		try {
			manager.merge(usuarioHasEquipe);
			trx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
