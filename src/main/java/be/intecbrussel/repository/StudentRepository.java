package be.intecbrussel.repository;

import be.intecbrussel.config.JpaExecutor;
import be.intecbrussel.model.Student;
import java.util.List;

public class StudentRepository {

    // CREATE
    public void create(Student student) {
        JpaExecutor.executeInTransaction(em -> em.persist(student));
    }

    // READ by id
    public Student findById(Long id) {
        return JpaExecutor.execute(em -> em.find(Student.class, id));
    }

    // READ all
    public List<Student> findAll() {
        return JpaExecutor.execute(em ->
                em.createQuery("SELECT s FROM Student s", Student.class)
                        .getResultList());
    }

    // UPDATE (обновляет существующего студента)
    public void update(Student student) {
        JpaExecutor.executeInTransaction(em -> em.merge(student));
    }

    // DELETE by id
    public void delete(Long id) {
        JpaExecutor.executeInTransaction(em -> {
            Student student = em.find(Student.class, id);
            if (student != null) {
                em.remove(student);
            }
        });
    }
}
