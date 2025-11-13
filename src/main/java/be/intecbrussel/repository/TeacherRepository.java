package be.intecbrussel.repository;

import be.intecbrussel.config.JpaConfig;
import be.intecbrussel.model.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class TeacherRepository {

    public void create(Teacher teacher) {
        EntityManager em = JpaConfig.getEntityManager();
        em.getTransaction().begin();
        em.persist(teacher);
        em.getTransaction().commit();
        em.close();
    }

    public Teacher findById(Long id) {
        EntityManager em = JpaConfig.getEntityManager();
        em.getTransaction().begin();
        Teacher teacher = em.find(Teacher.class, id);
        em.getTransaction().commit();
        em.close();
        return teacher;
    }

    public List<Teacher> findAll() {
        EntityManager em = JpaConfig.getEntityManager();
        em.getTransaction().begin();
        TypedQuery<Teacher> query =
                em.createQuery("SELECT t FROM Teacher t", Teacher.class);
        List<Teacher> teachers = query.getResultList();
        em.getTransaction().commit();
        em.close();
        return teachers;
    }

    public void update(Teacher teacher) {
        EntityManager em = JpaConfig.getEntityManager();
        em.getTransaction().begin();
        em.merge(teacher);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(Long id) {
        EntityManager em = JpaConfig.getEntityManager();
        em.getTransaction().begin();
        Teacher teacher = em.find(Teacher.class, id);
        if (teacher != null) {
            em.remove(teacher);
        }
        em.getTransaction().commit();
        em.close();
    }
}
