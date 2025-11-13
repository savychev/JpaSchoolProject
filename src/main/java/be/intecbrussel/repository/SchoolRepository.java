package be.intecbrussel.repository;

import be.intecbrussel.config.JpaConfig;
import be.intecbrussel.model.School;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class SchoolRepository {

    public void create(School school) {
        EntityManager em = JpaConfig.getEntityManager();
        em.getTransaction().begin();
        em.persist(school);
        em.getTransaction().commit();
        em.close();
    }

    public School findById(Long id) {
        EntityManager em = JpaConfig.getEntityManager();
        em.getTransaction().begin();
        School school = em.find(School.class, id);
        em.getTransaction().commit();
        em.close();
        return school;
    }

    public List<School> findAll() {
        EntityManager em = JpaConfig.getEntityManager();
        em.getTransaction().begin();
        TypedQuery<School> query =
                em.createQuery("SELECT s FROM School s", School.class);
        List<School> schools = query.getResultList();
        em.getTransaction().commit();
        em.close();
        return schools;
    }

    public void update(School school) {
        EntityManager em = JpaConfig.getEntityManager();
        em.getTransaction().begin();
        em.merge(school);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(Long id) {
        EntityManager em = JpaConfig.getEntityManager();
        em.getTransaction().begin();
        School school = em.find(School.class, id);
        if (school != null) {
            em.remove(school);
        }
        em.getTransaction().commit();
        em.close();
    }
}
