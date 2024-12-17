package ec.edu.uce.jakarta.notifications.jpa;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceUnit;

import java.util.Collections;
import java.util.List;

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

    //Obtener los estudiantes de un curso
    public List<Student> getAllStudentsOfCourse(int courseId) {
        String query = "SELECT s FROM Student s JOIN FETCH s.course c WHERE c.id = :courseId";

        try {
            return entityManager.createQuery(query, Student.class)
                    .setParameter("courseId", courseId)
                    .getResultList();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}
