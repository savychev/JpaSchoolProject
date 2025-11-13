package be.intecbrussel.repository;

import be.intecbrussel.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class StudentRepository {

    private EntityManager entityManager;

    public StudentRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void create(Student student) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(student);
        transaction.commit();
    }

    public Student findById(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Student student = entityManager.find(Student.class, id);
        transaction.commit();
        return student;
    }

    public List<Student> findAll() {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        List<Student> students = entityManager
                .createQuery("SELECT s FROM Student s", Student.class)
                .getResultList();
        transaction.commit();
        return students;
    }

    public void update(Student student) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(student);
        transaction.commit();
    }

    public void delete(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Student student = entityManager.find(Student.class, id);
        if (student != null) {
            entityManager.remove(student);
        }
        transaction.commit();
    }
}
