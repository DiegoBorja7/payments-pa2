package ec.edu.uce.jakarta.notifications.jpa;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceUnit;

@Stateless
public class StudentServices {
    @PersistenceUnit(unitName = "UnitPersistenceDB")
    private EntityManager entityManager;

    public StudentServices() {
    }

    //create
    public void createStudent(Student student) {
        try {
            entityManager.persist(student); // Persiste el nuevo estudiante
        } catch (Exception e) {
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
