package be.intecbrussel.repository;

import be.intecbrussel.model.School;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class SchoolRepository {

    private EntityManager entityManager;

    public SchoolRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void create(School school) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(school);
        transaction.commit();
    }

    public School findById(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        School school = entityManager.find(School.class, id);
        transaction.commit();
        return school;
    }

    public List<School> findAll() {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        List<School> schools = entityManager
                .createQuery("SELECT s FROM School s", School.class)
                .getResultList();
        transaction.commit();
        return schools;
    }

    public void update(School school) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(school);
        transaction.commit();
    }

    public void delete(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        School school = entityManager.find(School.class, id);
        if (school != null) {
            entityManager.remove(school);
        }
        transaction.commit();
    }
}
