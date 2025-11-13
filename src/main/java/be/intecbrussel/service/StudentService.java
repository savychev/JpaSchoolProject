package be.intecbrussel.service;

import be.intecbrussel.model.Student;
import be.intecbrussel.repository.StudentRepository;

import java.util.List;

public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void addStudent(Student student) {
        studentRepository.create(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void updateStudent(Long id, Student newStudentData) {
        Student existingStudent = studentRepository.findById(id);
        if (existingStudent != null) {
            existingStudent.setFirstname(newStudentData.getFirstname());
            existingStudent.setLastname(newStudentData.getLastname());
            existingStudent.setSchool(newStudentData.getSchool());
            studentRepository.update(existingStudent);
        }
    }

    public void removeStudent(Long id) {
        studentRepository.delete(id);
    }
}
