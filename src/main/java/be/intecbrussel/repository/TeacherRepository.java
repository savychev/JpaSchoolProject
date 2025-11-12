package be.intecbrussel.repository;

import be.intecbrussel.config.JpaConfig;
import be.intecbrussel.model.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class TeacherRepository {

    // CREATE
    public void create(Teacher teacher) {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(teacher);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // READ by id
    public Teacher findById(Long id) {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            return em.find(Teacher.class, id);
        } finally {
            em.close();
        }
    }

    // READ all
    public List<Teacher> findAll() {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            TypedQuery<Teacher> query =
                    em.createQuery("SELECT t FROM Teacher t", Teacher.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // UPDATE
    public void update(Teacher teacher) {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(teacher);
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
            Teacher t = em.find(Teacher.class, id);
            if (t != null) em.remove(t);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
