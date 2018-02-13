package util.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class EntityManagerProducer {

	private static EntityManager entityManagerCetecop = null;

	private static EntityManager entityManagerDomjudge = null;

	public static EntityManager getentityManagerCetecop() {
		if (entityManagerCetecop == null)
			return Persistence.createEntityManagerFactory("cetecop").createEntityManager();
		else if (!entityManagerCetecop.isOpen())
			return Persistence.createEntityManagerFactory("cetecop").createEntityManager();
		else
			return entityManagerCetecop;
	}

	public static EntityManager getentityManagerDomjudge() {
		if (entityManagerDomjudge == null)
			return Persistence.createEntityManagerFactory("domjudge").createEntityManager();
		else if (!entityManagerDomjudge.isOpen())
			return Persistence.createEntityManagerFactory("domjudge").createEntityManager();
		else
			return entityManagerDomjudge;
	}
	
	// private EntityManagerFactory factory;

	// public EntityManagerProducer() {
	// factory = Persistence.createEntityManagerFactory("cetecop");
	// }

	// @Produces @RequestScoped
	// public EntityManager createEntityManager() {
	// return factory.createEntityManager();
	// }

	// public void closeEntityManager(@Disposes EntityManager manager) {
	// manager.close();
	// }

}