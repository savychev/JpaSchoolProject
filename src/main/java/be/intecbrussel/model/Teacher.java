package be.intecbrussel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinColumn;

import java.util.List;
import java.util.ArrayList;

// This class represents a teacher record stored in the database.
@Entity
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // This field holds the unique number for each teacher.
    private Long id;

    // This field stores the teacher's first name for reference.
    private String firstname;

    // This field stores the teacher's last name for reference.
    private String lastname;

    @ManyToOne
    @JoinColumn(name = "school_id")
    // This field links the teacher to the school they work at.
    private School school;

    @ManyToMany(mappedBy = "teachers")
    // This list tracks all students taught by this teacher.
    private List<Student> students = new ArrayList<>();

    // This empty constructor allows JPA to create teacher objects.
    public Teacher() {
    }

    // This getter returns the teacher's unique number.
    public Long getId() {
        return id;
    }

    // This getter returns the teacher's first name.
    public String getFirstname() {
        return firstname;
    }

    // This setter updates the teacher's first name.
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    // This getter returns the teacher's last name.
    public String getLastname() {
        return lastname;
    }

    // This setter updates the teacher's last name.
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    // This getter returns the school connected to this teacher.
    public School getSchool() {
        return school;
    }

    // This setter links the teacher to a school.
    public void setSchool(School school) {
        this.school = school;
    }

    // This getter returns the students linked to this teacher.
    public List<Student> getStudents() {
        return students;
    }
}
