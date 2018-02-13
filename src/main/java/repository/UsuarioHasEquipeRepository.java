package repository;

import java.io.Serializable;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.entity.UsuarioHasEquipe;
import util.jpa.EntityManagerProducer;
public class UsuarioHasEquipeRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManager manager = EntityManagerProducer.getentityManagerCetecop();;
	
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
