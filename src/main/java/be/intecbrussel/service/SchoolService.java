package be.intecbrussel.service;

import be.intecbrussel.model.School;
import be.intecbrussel.model.Student;
import be.intecbrussel.repository.SchoolRepository;
import be.intecbrussel.repository.StudentRepository;

import java.util.List;

public class SchoolService {

    private final SchoolRepository schools = new SchoolRepository();
    private final StudentRepository students = new StudentRepository();

    // --- CRUD по школе ---

    public School addSchool(String name, String city) {
        School s = new School(name, city);
        schools.create(s);
        return s;
    }

    public School getSchool(Long id) {
        return schools.findById(id);
    }

    public List<School> getAllSchools() {
        return schools.findAll();
    }

    public School updateSchool(Long id, String newName, String newCity) {
        School s = schools.findById(id);
        if (s == null) return null;
        if (newName != null) s.setName(newName);
        if (newCity != null) s.setCity(newCity);
        schools.update(s);
        return s;
    }

    public boolean removeSchool(Long id) {
        School s = schools.findById(id);
        if (s == null) return false;
        schools.delete(id);
        return true;
    }

    // --- Полезный сценарий: создать школу и сразу студентов ---

    public School addSchoolWithStudents(String name, String city, List<Student> initialStudents) {
        // 1) создаём школу
        School school = new School(name, city);

        // 2) связываем студентов с этой школой (двусторонне)
        if (initialStudents != null) {
            for (Student st : initialStudents) {
                school.addStudent(st); // выставит st.setSchool(school)
            }
        }

        // 3) сохраняем каскадом школу и всех студентов
        schools.create(school); // cascade = ALL в School.students
        return school;
    }

    // Утилита: добавить одного существующего/нового студента к школе
    public Student addStudentToSchool(Long schoolId, String firstname, String lastname) {
        School school = schools.findById(schoolId);
        if (school == null) return null;

        Student st = new Student(firstname, lastname);
        school.addStudent(st);      // двусторонняя связь
        schools.update(school);     // merge школы протолкнёт изменения (FK school_id) и вставит студента
        return st;
    }
}
