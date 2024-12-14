package ec.edu.uce.jakarta.payments.services;

import ec.edu.uce.jakarta.payments.model.Product;
import jakarta.persistence.EntityManager;

public class ProductServices {
    private EntityManager entityManager;

    public ProductServices(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //crear producto
    public void createProduct(Product product) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(product);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    //leer usuario
    public Product findByIDProduct(int id) {
        Product product = null;

        try {
            entityManager.getTransaction().begin();
            product = entityManager.find(Product.class, id);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return product;
    }

    //actualizar
    public void updateProduct(Product product) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(product);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    //eliminar
    public void deleteProduct(int id) {
        Product product = findByIDProduct(id);

        try {
            entityManager.getTransaction().begin();
            entityManager.remove(product);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }
}
