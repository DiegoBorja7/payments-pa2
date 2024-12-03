package ec.edu.uce.jakarta.payments.services;

import ec.edu.uce.jakarta.payments.classes.Account;
import jakarta.persistence.EntityManager;

public class AccountServices {
    private EntityManager entityManager;

    public AccountServices(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //crear cuenta
    public void createAccount(Account account) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(account);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    //leer usuario
    public Account findByIDAccount(int id) {
        Account account = null;

        try {
            entityManager.getTransaction().begin();
            account = entityManager.find(Account.class, id);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return account;
    }

    //actualizar
    public void updateAccount(Account account) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(account);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    //eliminar
    public void deleteAccount(int id) {
        Account account = findByIDAccount(id);

        try {
            entityManager.getTransaction().begin();
            entityManager.remove(account);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }
}