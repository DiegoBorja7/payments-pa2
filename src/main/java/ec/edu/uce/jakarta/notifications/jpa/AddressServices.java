package ec.edu.uce.jakarta.notifications.jpa;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceUnit;

@Stateless
public class AddressServices {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public AddressServices() {
        entityManagerFactory = Persistence.createEntityManagerFactory("UnitPersistenceDB");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void save(Address address) {
        entityManager.getTransaction().begin();
        try {

            entityManager.persist(address);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }
}
