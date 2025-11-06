package be.intecbrussel.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    private String lastname;

    // Один студент принадлежит одной школе
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    private School school;

    // Один студент может учиться у многих учителей
    @ManyToMany(mappedBy = "students")
    private Set<Teacher> teachers = new HashSet<>();

    public Student() {}

    public Student(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    // getters & setters
    public Long getId() { return id; }
    public String getFirstname() { return firstname; }
    public String getLastname() { return lastname; }
    public School getSchool() { return school; }
    public Set<Teacher> getTeachers() { return teachers; }

    public void setId(Long id) { this.id = id; }
    public void setFirstname(String firstname) { this.firstname = firstname; }
    public void setLastname(String lastname) { this.lastname = lastname; }
    public void setSchool(School school) { this.school = school; }
}