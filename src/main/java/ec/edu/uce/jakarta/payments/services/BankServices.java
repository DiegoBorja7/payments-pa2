package ec.edu.uce.jakarta.payments.services;

import ec.edu.uce.jakarta.payments.classes.Bank;
import jakarta.persistence.EntityManager;

public class BankServices {
    private EntityManager entityManager;

    public BankServices(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //crear banco
    public void createBank(Bank bank) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(bank);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    //leer usuario
    public Bank findByIDBank(int id) {
        Bank bank = null;

        try {
            entityManager.getTransaction().begin();
            bank = entityManager.find(Bank.class, id);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return bank;
    }

    //actualizar
    public void updateBank(Bank bank) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(bank);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    //eliminar
    public void deleteBank(int id) {
        Bank bank = findByIDBank(id);

        try {
            entityManager.getTransaction().begin();
            entityManager.remove(bank);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }
}
