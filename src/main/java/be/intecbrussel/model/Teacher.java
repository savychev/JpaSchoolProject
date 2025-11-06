package be.intecbrussel.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    private String lastname;

    // Каждый учитель работает в одной школе
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    private School school;

    // Учитель может обучать многих студентов
    @ManyToMany
    @JoinTable(
            name = "student_teacher",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<Student> students = new HashSet<>();

    public Teacher() {}

    public Teacher(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    // getters & setters
    public Long getId() { return id; }
    public String getFirstname() { return firstname; }
    public String getLastname() { return lastname; }
    public School getSchool() { return school; }
    public Set<Student> getStudents() { return students; }

    public void setId(Long id) { this.id = id; }
    public void setFirstname(String firstname) { this.firstname = firstname; }
    public void setLastname(String lastname) { this.lastname = lastname; }
    public void setSchool(School school) { this.school = school; }
}
