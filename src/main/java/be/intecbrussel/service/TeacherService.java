package be.intecbrussel.service;

import be.intecbrussel.model.Teacher;
import be.intecbrussel.repository.TeacherRepository;

import java.util.List;

// This service class connects teacher operations to the repository layer.
public class TeacherService {

    // This field stores the repository that talks to the database.
    private TeacherRepository teacherRepository;

    // This constructor supplies the repository so the service can reuse it.
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    // This method asks the repository to save a new teacher.
    public void addTeacher(Teacher teacher) {
        teacherRepository.create(teacher);
    }

    // This method returns all teachers by delegating to the repository.
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    // This method updates a teacher with new data if the teacher exists.
    public void updateTeacher(Long id, Teacher newTeacherData) {
        // This call fetches the current teacher from the database.
        Teacher existingTeacher = teacherRepository.findById(id);
        if (existingTeacher != null) {
            existingTeacher.setFirstname(newTeacherData.getFirstname());
            existingTeacher.setLastname(newTeacherData.getLastname());
            existingTeacher.setSchool(newTeacherData.getSchool());
            teacherRepository.update(existingTeacher);
        }
    }

    // This method removes a teacher by telling the repository to delete it.
    public void removeTeacher(Long id) {
        teacherRepository.delete(id);
    }
}
