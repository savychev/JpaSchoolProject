package be.intecbrussel.repository;

import be.intecbrussel.config.JpaExecutor;
import be.intecbrussel.model.School;
import java.util.List;

public class SchoolRepository {

    // CREATE
    public void create(School school) {
        JpaExecutor.executeInTransaction(em -> em.persist(school));
    }

    // READ by id
    public School findById(Long id) {
        return JpaExecutor.execute(em -> em.find(School.class, id));
    }

    // READ all
    public List<School> findAll() {
        return JpaExecutor.execute(em ->
                em.createQuery("SELECT s FROM School s", School.class)
                        .getResultList());
    }

    // UPDATE
    public void update(School school) {
        JpaExecutor.executeInTransaction(em -> em.merge(school));
    }

    // DELETE
    public void delete(Long id) {
        JpaExecutor.executeInTransaction(em -> {
            School school = em.find(School.class, id);
            if (school != null) {
                em.remove(school);
            }
        });
    }
}
