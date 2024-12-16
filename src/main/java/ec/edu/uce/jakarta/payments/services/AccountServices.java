package ec.edu.uce.jakarta.payments.services;

import ec.edu.uce.jakarta.payments.model.Account;
import ec.edu.uce.jakarta.payments.model.Bank;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

@Stateless
public class AccountServices {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public AccountServices() {
        entityManagerFactory = Persistence.createEntityManagerFactory("UnitPersistencePaymentsDB");
        entityManager = entityManagerFactory.createEntityManager();
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

    public List<Account> getAllAccounts() {
        List<Account> accounts = null;
        String query = "SELECT a FROM Account a";
        try {
            entityManager.getTransaction().begin();
            accounts = entityManager.createQuery(query, Account.class).getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return accounts;
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