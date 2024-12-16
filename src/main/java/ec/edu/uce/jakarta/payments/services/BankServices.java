package ec.edu.uce.jakarta.payments.services;

import ec.edu.uce.jakarta.payments.model.Bank;
import ec.edu.uce.jakarta.payments.model.User;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

@Stateless
public class BankServices {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public BankServices() {
        entityManagerFactory = Persistence.createEntityManagerFactory("UnitPersistencePaymentsDB");
        entityManager = entityManagerFactory.createEntityManager();
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

    public List<Bank> getAllBanks() {
        List<Bank> banks = null;
        String query = "SELECT b FROM Bank b";
        try {
            entityManager.getTransaction().begin();
            banks = entityManager.createQuery(query, Bank.class).getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return banks;
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
