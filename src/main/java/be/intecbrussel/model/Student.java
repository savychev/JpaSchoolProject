package be.intecbrussel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;

import java.util.List;
import java.util.ArrayList;

// This class defines how a student is stored and managed in the database.
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // This field stores the unique number given to each student in the database.
    private Long id;

    // This field keeps the student's first name so it can be saved and retrieved.
    private String firstname;

    // This field keeps the student's last name so it can be saved and retrieved.
    private String lastname;

    @ManyToOne
    @JoinColumn(name = "school_id")
    // This field links the student to the school they belong to.
    private School school;

    @ManyToMany
    @JoinTable(
            name = "student_teacher",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    // This list keeps all teachers that work with this student.
    private List<Teacher> teachers = new ArrayList<>();

    // This empty constructor is needed by JPA to create student objects.
    public Student() {
    }

    // This getter returns the student's unique number so other parts can read it.
    public Long getId() {
        return id;
    }

    // This getter returns the student's first name for display or processing.
    public String getFirstname() {
        return firstname;
    }

    // This setter updates the student's first name when it changes.
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    // This getter returns the student's last name for display or processing.
    public String getLastname() {
        return lastname;
    }

    // This setter updates the student's last name when it changes.
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    // This getter returns the school linked to this student.
    public School getSchool() {
        return school;
    }

    // This setter links the student to a school.
    public void setSchool(School school) {
        this.school = school;
    }

    // This getter returns all teachers connected to this student.
    public List<Teacher> getTeachers() {
        return teachers;
    }
}
