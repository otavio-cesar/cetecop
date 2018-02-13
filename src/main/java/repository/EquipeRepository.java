package repository;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import model.entity.Equipe;
import model.entity.Usuario;
import model.entity.UsuarioHasEquipe;
import util.jpa.EntityManagerProducer;

public class EquipeRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	// TODO injecao automatica

	public Equipe guardar(Equipe equipe) {
		Equipe retorno;

		EntityManager manager = EntityManagerProducer.getentityManagerCetecop();

		EntityTransaction trx = manager.getTransaction();

		trx.begin();

		try {
			retorno = manager.merge(equipe);
			trx.commit();
		} catch (Exception e) {
			retorno = null;
		}

		//manager.close();

		return retorno;
	}

	public Equipe buscarPorId(Integer id) {
		Equipe equipe;

		EntityManager manager = EntityManagerProducer.getentityManagerCetecop();

		equipe = manager.find(Equipe.class, id);

		//manager.close();

		return equipe;
	}

	public Equipe buscarEquipeUnica(Usuario usuario) {
		Equipe equipe = null;
		List<UsuarioHasEquipe> usuariosHasEquipe;

		EntityManager manager = EntityManagerProducer.getentityManagerCetecop();

		usuariosHasEquipe = manager
				.createQuery("from UsuarioHasEquipe where usuario_id = :usuarioId", UsuarioHasEquipe.class)
				.setParameter("usuarioId", usuario.getId())
				.getResultList();

		for (UsuarioHasEquipe usuarioHasEquipe : usuariosHasEquipe) {
			if(usuarioHasEquipe.getEquipe().isEquipeOneUser()) {
				equipe = usuarioHasEquipe.getEquipe();
			}
		}
		
		//manager.close();

		return equipe;
	}
}
