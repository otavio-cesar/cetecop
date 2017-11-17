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

	public Usuario guardar(Usuario usuario) {
		EntityTransaction trx = manager.getTransaction();
		
		trx.begin();

		try {
			usuario = manager.merge(usuario);
			trx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return usuario;
	}

	public Usuario buscarPorEmail(String email) {
		Usuario usuario;
		
		EntityTransaction trx = manager.getTransaction();
		
		trx.begin();

		try {
			usuario = manager.createQuery("from Usuario where lower(email) = :email",	Usuario.class)
					.setParameter("email", email.toLowerCase())
					.getSingleResult();
			trx.commit();
		} catch (Exception e) {
			usuario = null;
		}

		return usuario;
	}
}
