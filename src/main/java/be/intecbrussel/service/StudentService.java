package be.intecbrussel.service;

import be.intecbrussel.model.Student;
import be.intecbrussel.repository.StudentRepository;

import java.util.List;

public class StudentService {

    private final StudentRepository repo = new StudentRepository();

    // создать нового студента
    public Student addStudent(String firstname, String lastname) {
        Student s = new Student(firstname, lastname);
        repo.create(s);
        return s;
    }

    // получить одного студента
    public Student getStudent(Long id) {
        return repo.findById(id);
    }

    // получить всех студентов
    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    // обновить данные (по-простому)
    public Student updateStudent(Long id, String newFirstname, String newLastname) {
        Student existing = repo.findById(id);
        if (existing == null) return null;
        if (newFirstname != null) existing.setFirstname(newFirstname);
        if (newLastname  != null) existing.setLastname(newLastname);
        repo.update(existing);
        return existing;
    }

    // удалить по id
    public boolean removeStudent(Long id) {
        Student existing = repo.findById(id);
        if (existing == null) return false;
        repo.delete(id);
        return true;
    }
}
