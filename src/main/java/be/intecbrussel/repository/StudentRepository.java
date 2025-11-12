package be.intecbrussel.repository;

import be.intecbrussel.config.JpaConfig;
import be.intecbrussel.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class StudentRepository {

    // CREATE
    public void create(Student student) {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(student);              // INSERT
            em.getTransaction().commit();     // фиксируем
        } finally {
            em.close();
        }
    }

    // READ by id
    public Student findById(Long id) {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            return em.find(Student.class, id); // SELECT ... WHERE id=?
        } finally {
            em.close();
        }
    }

    // READ all
    public List<Student> findAll() {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            TypedQuery<Student> q =
                    em.createQuery("SELECT s FROM Student s", Student.class); // JPQL
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    // UPDATE (обновляет существующего студента)
    public void update(Student student) {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(student);                // UPDATE (или attach + diff)
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // DELETE by id
    public void delete(Long id) {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            Student s = em.find(Student.class, id);
            if (s != null) em.remove(s);      // DELETE
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
