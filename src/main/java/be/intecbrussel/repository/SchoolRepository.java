package be.intecbrussel.repository;

import be.intecbrussel.config.JpaConfig;
import be.intecbrussel.model.School;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class SchoolRepository {

    // CREATE
    public void create(School school) {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(school);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // READ by id
    public School findById(Long id) {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            return em.find(School.class, id);
        } finally {
            em.close();
        }
    }

    // READ all
    public List<School> findAll() {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            TypedQuery<School> query =
                    em.createQuery("SELECT s FROM School s", School.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // UPDATE
    public void update(School school) {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(school);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // DELETE
    public void delete(Long id) {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            School s = em.find(School.class, id);
            if (s != null) em.remove(s);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
