package be.intecbrussel.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "schools")
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String city;

    // Одна школа имеет много учителей
    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Teacher> teachers = new ArrayList<>();

    // Одна школа имеет много студентов
    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Student> students = new ArrayList<>();

    public School() {}

    public School(String name, String city) {
        this.name = name;
        this.city = city;
    }

    // методы удобного добавления
    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
        teacher.setSchool(this);
    }

    public void addStudent(Student student) {
        students.add(student);
        student.setSchool(this);
    }

    // getters & setters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getCity() { return city; }
    public List<Teacher> getTeachers() { return teachers; }
    public List<Student> getStudents() { return students; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setCity(String city) { this.city = city; }
}
