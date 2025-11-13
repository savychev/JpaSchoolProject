package be.intecbrussel.repository;

import be.intecbrussel.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

// This class handles database actions for student records.
public class StudentRepository {

    // This field gives access to the database connection and operations.
    private EntityManager entityManager;

    // This constructor receives the entity manager that will run database work.
    public StudentRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // This method saves a new student to the database inside a transaction.
    public void create(Student student) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(student);
        transaction.commit();
    }

    // This method finds a student by id while wrapping the work in a transaction.
    public Student findById(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Student student = entityManager.find(Student.class, id);
        transaction.commit();
        return student;
    }

    // This method loads all students by running a query inside a transaction.
    public List<Student> findAll() {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        List<Student> students = entityManager
                .createQuery("SELECT s FROM Student s", Student.class)
                .getResultList();
        transaction.commit();
        return students;
    }

    // This method updates a student entry and commits the change.
    public void update(Student student) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(student);
        transaction.commit();
    }

    // This method removes a student entry if it exists, using a transaction.
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
