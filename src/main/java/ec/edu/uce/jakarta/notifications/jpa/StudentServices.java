package ec.edu.uce.jakarta.notifications.jpa;


import jakarta.persistence.EntityManager;

public class StudentServices {
    private EntityManager entityManager;

    public StudentServices(EntityManager entityManager) {
        this.entityManager = entityManager;
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
            entityManager.getTransaction().begin();
            entityManager.find(Student.class, id);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }

            e.printStackTrace();
        }
        return student;
    }

    //update
    public void update(Student student) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(student);
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
        Student student = findByID(id);

        try {
            entityManager.getTransaction().begin();
            entityManager.merge(student);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }

            e.printStackTrace();
        }
    }
}
