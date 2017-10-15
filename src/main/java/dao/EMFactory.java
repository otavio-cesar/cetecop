package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class EMFactory<T> implements IDAO<T> {

    protected static EntityManager entityManager;

    protected EntityManager getEntityManager() {
        if (entityManager == null) {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("sistemaMaratonaUnit");
            entityManager = factory.createEntityManager();
        }

        return entityManager;
    }

    @Override
    public void create(T value) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(value);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
        }
    }
    
    @Override
    public T update(T value) {
        T t = null;
        try {
            entityManager.getTransaction().begin();
            t = entityManager.merge(value);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
        }
        return t;
    }
    
    @Override
    public T remove(T value) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(value);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
        }
        return value;
    }

}
