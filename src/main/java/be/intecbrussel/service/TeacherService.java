package be.intecbrussel.service;

import be.intecbrussel.model.Teacher;
import be.intecbrussel.repository.TeacherRepository;

import java.util.List;

public class TeacherService {

    private TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public void addTeacher(Teacher teacher) {
        teacherRepository.create(teacher);
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public void updateTeacher(Long id, Teacher newTeacherData) {
        Teacher existingTeacher = teacherRepository.findById(id);
        if (existingTeacher != null) {
            existingTeacher.setFirstname(newTeacherData.getFirstname());
            existingTeacher.setLastname(newTeacherData.getLastname());
            existingTeacher.setSchool(newTeacherData.getSchool());
            teacherRepository.update(existingTeacher);
        }
    }

    public void removeTeacher(Long id) {
        teacherRepository.delete(id);
    }
}
