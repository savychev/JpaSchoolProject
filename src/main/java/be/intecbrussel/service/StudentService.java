package be.intecbrussel.service;

import be.intecbrussel.model.Student;
import be.intecbrussel.repository.StudentRepository;

import java.util.List;

// This service class coordinates student actions between the app and the repository.
public class StudentService {

    // This field holds the repository used to talk to the database layer.
    private StudentRepository studentRepository;

    // This constructor injects the repository so the service can call it.
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // This method passes a new student to the repository to be stored.
    public void addStudent(Student student) {
        studentRepository.create(student);
    }

    // This method returns every student known by asking the repository.
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // This method updates a student if it exists by copying over new values.
    public void updateStudent(Long id, Student newStudentData) {
        // This call loads the current student so it can be changed safely.
        Student existingStudent = studentRepository.findById(id);
        if (existingStudent != null) {
            existingStudent.setFirstname(newStudentData.getFirstname());
            existingStudent.setLastname(newStudentData.getLastname());
            existingStudent.setSchool(newStudentData.getSchool());
            studentRepository.update(existingStudent);
        }
    }

    // This method removes a student by asking the repository to delete it.
    public void removeStudent(Long id) {
        studentRepository.delete(id);
    }
}
