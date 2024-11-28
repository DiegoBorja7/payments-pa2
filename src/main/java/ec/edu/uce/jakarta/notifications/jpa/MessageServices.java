package ec.edu.uce.jakarta.notifications.jpa;

import jakarta.persistence.EntityManager;

public class MessageServices {
    private EntityManager entityManager;

    public MessageServices(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public void createMessge(Message message) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(message);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    //read
    public Message findByID(int id) {
        Message message = null;

        try {
            entityManager.getTransaction().begin();
            entityManager.find(Message.class, id);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }

            e.printStackTrace();
        }
        return message;
    }

    //update
    public void update(Message message) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(message);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }

            e.printStackTrace();
        }
    }

    //delete
    public void delete(int id) {
        Message message = findByID(id);

        try {
            entityManager.getTransaction().begin();
            entityManager.merge(message);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }

            e.printStackTrace();
        }
    }
}
