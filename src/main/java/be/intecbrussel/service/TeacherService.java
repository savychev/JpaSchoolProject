package be.intecbrussel.service;

import be.intecbrussel.model.School;
import be.intecbrussel.model.Student;
import be.intecbrussel.model.Teacher;
import be.intecbrussel.repository.SchoolRepository;
import be.intecbrussel.repository.StudentRepository;
import be.intecbrussel.repository.TeacherRepository;
import be.intecbrussel.config.JpaConfig;
import jakarta.persistence.EntityManager;


import java.util.List;

public class TeacherService {

    private final TeacherRepository teachers = new TeacherRepository();
    private final SchoolRepository schools = new SchoolRepository();
    private final StudentRepository students = new StudentRepository();

    // ========== CRUD по учителю ==========

    public Teacher addTeacher(String firstname, String lastname) {
        Teacher t = new Teacher(firstname, lastname);
        teachers.create(t);
        return t;
    }

    public Teacher getTeacher(Long id) {
        return teachers.findById(id);
    }

    public List<Teacher> getAllTeachers() {
        return teachers.findAll();
    }

    public Teacher updateTeacher(Long id, String fn, String ln) {
        Teacher t = teachers.findById(id);
        if (t == null) return null;
        if (fn != null) t.setFirstname(fn);
        if (ln != null) t.setLastname(ln);
        teachers.update(t);
        return t;
    }

    public boolean removeTeacher(Long id) {
        Teacher t = teachers.findById(id);
        if (t == null) return false;
        teachers.delete(id);
        return true;
    }

    // ========== Привязать учителя к школе (Many-To-One) ==========

    public Teacher assignTeacherToSchool(Long teacherId, Long schoolId) {
        Teacher t = teachers.findById(teacherId);
        School s = schools.findById(schoolId);
        if (t == null || s == null) return null;

        t.setSchool(s);       // ⚠️ НЕ ТРОГАЕМ school.getTeachers() (LAZY SAFE)
        teachers.update(t);   // UPDATE teacher SET school_id = ?
        return t;
    }

    // ========== Привязать учителя к студенту (Many-to-Many) ==========

    public boolean addStudentToTeacher(Long teacherId, Long studentId) {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            em.getTransaction().begin();

            // читаем учителя и студента в ЭТОМ же EntityManager
            Teacher t = em.find(Teacher.class, teacherId);
            Student s = em.find(Student.class, studentId);

            if (t == null || s == null) {
                em.getTransaction().rollback();
                return false;
            }

            // теперь t — managed, и его коллекции можно трогать спокойно
            t.addStudent(s);  // здесь Hibernate НЕ ругается — сессия открыта

            // достаточно коммита: Hibernate сам обновит join-таблицу student_teacher
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }


    // ========== Удалить связь учитель ↔ студент ==========

    public boolean removeStudentFromTeacher(Long teacherId, Long studentId) {
        Teacher t = teachers.findById(teacherId);
        Student s = students.findById(studentId);

        if (t == null || s == null) return false;

        t.removeStudent(s);   // двусторонняя чистка
        teachers.update(t);   // Hibernate удалит строку из student_teacher
        return true;
    }
}
