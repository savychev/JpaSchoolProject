package be.intecbrussel.repository;

import be.intecbrussel.config.JpaExecutor;
import be.intecbrussel.model.Teacher;
import java.util.List;

public class TeacherRepository {

    // CREATE
    public void create(Teacher teacher) {
        JpaExecutor.executeInTransaction(em -> em.persist(teacher));
    }

    // READ by id
    public Teacher findById(Long id) {
        return JpaExecutor.execute(em -> em.find(Teacher.class, id));
    }

    // READ all
    public List<Teacher> findAll() {
        return JpaExecutor.execute(em ->
                em.createQuery("SELECT t FROM Teacher t", Teacher.class)
                        .getResultList());
    }

    // UPDATE
    public void update(Teacher teacher) {
        JpaExecutor.executeInTransaction(em -> em.merge(teacher));
    }

    // DELETE
    public void delete(Long id) {
        JpaExecutor.executeInTransaction(em -> {
            Teacher teacher = em.find(Teacher.class, id);
            if (teacher != null) {
                em.remove(teacher);
            }
        });
    }
}
