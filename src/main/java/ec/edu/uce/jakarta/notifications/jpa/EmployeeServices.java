package ec.edu.uce.jakarta.notifications.jpa;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Collections;
import java.util.List;

@Stateless
public class EmployeeServices {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public EmployeeServices() {
        entityManagerFactory = Persistence.createEntityManagerFactory("UnitPersistenceDB");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void save(Employee employee) {
        entityManager.getTransaction().begin();
        try {

            entityManager.persist(employee);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public Employee findByIDEmployee(int id) {
        Employee employee = null;
        entityManager.getTransaction().begin();
        try {
            employee = entityManager.find(Employee.class, id);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return employee;
    }

    public List<Employee> getAllEmployees(){
        String query = "SELECT e FROM Employee e";

        try {
            return entityManager.createQuery(query, Employee.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<Employee> getAllEmployeesWithAddress() {
        String query = "SELECT e FROM Employee e JOIN FETCH e.address";

        try {
            return entityManager.createQuery(query, Employee.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
