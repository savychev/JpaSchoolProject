package be.intecbrussel.repository;

import be.intecbrussel.model.School;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

// This class manages database actions for school records.
public class SchoolRepository {

    // This field holds the entity manager used to talk to the database.
    private EntityManager entityManager;

    // This constructor assigns the entity manager that runs all school actions.
    public SchoolRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // This method saves a new school entry within a transaction.
    public void create(School school) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(school);
        transaction.commit();
    }

    // This method finds a school by id using a transaction.
    public School findById(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        School school = entityManager.find(School.class, id);
        transaction.commit();
        return school;
    }

    // This method returns every school by running a select query within a transaction.
    public List<School> findAll() {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        List<School> schools = entityManager
                .createQuery("SELECT s FROM School s", School.class)
                .getResultList();
        transaction.commit();
        return schools;
    }

    // This method updates a school entry and commits the transaction.
    public void update(School school) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(school);
        transaction.commit();
    }

    // This method removes a school if it is found, inside a transaction.
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
