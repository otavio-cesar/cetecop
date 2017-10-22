import java.util.ArrayList;
import java.util.HashSet;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.Problema;
import model.Taxonomia;

public class Teste {
	
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("PedidoPU");
		EntityManager manager = factory.createEntityManager();

		EntityTransaction trx = manager.getTransaction();

		trx.begin();
		manager.persist(new Taxonomia());
		trx.commit();

		trx.begin();
		Problema x = new Problema();

		ArrayList<Taxonomia> taxonomia = new ArrayList<>();
		Taxonomia t = new Taxonomia();
		t.setDescricao("ad");
		taxonomia.add(t);
		//x.setTaxonomia(taxonomia);

		manager.persist(x);
		trx.commit();

		manager.close();
	}
	
}
