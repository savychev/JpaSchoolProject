package be.intecbrussel.repository;

import be.intecbrussel.model.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

// This class manages database actions for teacher records.
public class TeacherRepository {

    // This field provides access to database operations for teachers.
    private EntityManager entityManager;

    // This constructor stores the entity manager that runs all teacher queries.
    public TeacherRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // This method adds a new teacher to the database using a transaction.
    public void create(Teacher teacher) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(teacher);
        transaction.commit();
    }

    // This method looks up a teacher by id inside a transaction.
    public Teacher findById(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Teacher teacher = entityManager.find(Teacher.class, id);
        transaction.commit();
        return teacher;
    }

    // This method returns all teachers by running a query with transaction control.
    public List<Teacher> findAll() {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        List<Teacher> teachers = entityManager
                .createQuery("SELECT t FROM Teacher t", Teacher.class)
                .getResultList();
        transaction.commit();
        return teachers;
    }

    // This method updates a teacher and commits the change.
    public void update(Teacher teacher) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(teacher);
        transaction.commit();
    }

    // This method deletes a teacher if found, inside a transaction.
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
