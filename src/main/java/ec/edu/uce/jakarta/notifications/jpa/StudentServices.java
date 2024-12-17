package ec.edu.uce.jakarta.notifications.jpa;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceUnit;

@Stateless
public class StudentServices {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public StudentServices() {
        entityManagerFactory = Persistence.createEntityManagerFactory("UnitPersistenceDB");
        entityManager = entityManagerFactory.createEntityManager();
    }

    //create
    public void createStudent(Student student) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(student); // Persiste el nuevo estudiante
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }


    //read
    public Student findByID(int id) {
        Student student = null;
        try {
            student = entityManager.find(Student.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return student;
    }

    //update
    public void update(Student student) {
        try {
            entityManager.merge(student);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //delete
    public void delete(int id) {
        Student student = findByID(id);

        try {
            entityManager.merge(student);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
