package be.intecbrussel.repository;

import be.intecbrussel.config.JpaConfig;
import be.intecbrussel.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class StudentRepository {

    public void create(Student student) {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Student findById(Long id) {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            return em.find(Student.class, id);
        } finally {
            em.close();
        }
    }

    public List<Student> findAll() {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            TypedQuery<Student> query =
                    em.createQuery("SELECT s FROM Student s", Student.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public void update(Student student) {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(student);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            Student s = em.find(Student.class, id);
            if (s != null) em.remove(s);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
