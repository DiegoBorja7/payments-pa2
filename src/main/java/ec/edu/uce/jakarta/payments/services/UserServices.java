package ec.edu.uce.jakarta.payments.services;

import ec.edu.uce.jakarta.payments.model.User;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Collections;
import java.util.List;

@Stateless
public class UserServices {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public UserServices() {
        entityManagerFactory = Persistence.createEntityManagerFactory("UnitPersistencePaymentsDB");
        entityManager = entityManagerFactory.createEntityManager();
    }

    //crear usuario
    public void createUser(User user) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = null;
        String query = "SELECT u FROM User u";
        try {
            entityManager.getTransaction().begin();
            users = entityManager.createQuery(query, User.class).getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return users;
    }

    //leer usuario
    public User findByIDUser(int id) {
        User user = null;

        try {
            entityManager.getTransaction().begin();
            user = entityManager.find(User.class, id);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return user;
    }

    //actualizar
    public void updateUser(User user) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(user);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    //eliminar
    public void deleteUser(int id) {
        User user = findByIDUser(id);

        try {
            entityManager.getTransaction().begin();
            entityManager.remove(user);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }
}
