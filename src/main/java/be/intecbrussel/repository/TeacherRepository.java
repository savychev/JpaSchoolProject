package be.intecbrussel.repository;

import be.intecbrussel.model.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class TeacherRepository {

    private EntityManager entityManager;

    public TeacherRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void create(Teacher teacher) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(teacher);
        transaction.commit();
    }

    public Teacher findById(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Teacher teacher = entityManager.find(Teacher.class, id);
        transaction.commit();
        return teacher;
    }

    public List<Teacher> findAll() {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        List<Teacher> teachers = entityManager
                .createQuery("SELECT t FROM Teacher t", Teacher.class)
                .getResultList();
        transaction.commit();
        return teachers;
    }

    public void update(Teacher teacher) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(teacher);
        transaction.commit();
    }

    public void delete(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Teacher teacher = entityManager.find(Teacher.class, id);
        if (teacher != null) {
            entityManager.remove(teacher);
        }
        transaction.commit();
    }
}
