package ec.edu.uce.jakarta.payments.services;

import ec.edu.uce.jakarta.payments.classes.Record;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@Stateless
public class RecordServices {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public RecordServices() {
        entityManagerFactory = Persistence.createEntityManagerFactory("UnitPersistencePaymentsDB");
        entityManager = entityManagerFactory.createEntityManager();
    }

    //crear cuenta
    public void createRecord(Record record) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(record);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    //leer usuario
    public Record findByIDRecord(int id) {
        Record record = null;

        try {
            entityManager.getTransaction().begin();
            record = entityManager.find(Record.class, id);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return record;
    }

    //actualizar
    public void updateRecord(Record record) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(record);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    //eliminar
    public void deleteRecord(int id) {
        Record record = findByIDRecord(id);

        try {
            entityManager.getTransaction().begin();
            entityManager.remove(record);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }
}
