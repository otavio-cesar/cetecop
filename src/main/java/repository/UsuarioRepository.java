package repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.entity.Usuario;

public class UsuarioRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public boolean guardar(Usuario usuario) {
		EntityTransaction trx = this.manager.getTransaction();
		trx.begin();

		try {
			this.manager.persist(usuario);
			trx.commit();
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	public Usuario buscarPorEmail(String email) {
		Usuario usuario = null;
		
		EntityTransaction trx = this.manager.getTransaction();
		trx.begin();

		try {
			usuario = this.manager.createQuery("from Usuario where lower(email) = :email",	Usuario.class)
					.setParameter("email", email.toLowerCase()).getSingleResult();
			trx.commit();
		} catch (Exception e) {
			// nenhum usuario encontrado
		}

		return usuario;
		
	}
}
