package be.intecbrussel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;

import java.util.List;
import java.util.ArrayList;

// This class models a school entry saved in the database.
@Entity
@Table(name = "schools")
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // This field keeps the unique number used to find the school.
    private Long id;

    // This field stores the official name of the school.
    private String name;

    // This field stores the city where the school is located.
    private String city;

    @OneToMany(mappedBy = "school")
    // This list holds all teachers that belong to this school.
    private List<Teacher> teachers = new ArrayList<>();

    @OneToMany(mappedBy = "school")
    // This list holds all students that attend this school.
    private List<Student> students = new ArrayList<>();

    // This empty constructor lets JPA create school objects when loading data.
    public School() {
    }

    // This getter returns the school's unique number.
    public Long getId() {
        return id;
    }

    // This getter returns the school's name.
    public String getName() {
        return name;
    }

    // This setter updates the school's name.
    public void setName(String name) {
        this.name = name;
    }

    // This getter returns the city of the school.
    public String getCity() {
        return city;
    }

    // This setter updates the city of the school.
    public void setCity(String city) {
        this.city = city;
    }

    // This getter returns all teachers linked to the school.
    public List<Teacher> getTeachers() {
        return teachers;
    }

    // This getter returns all students linked to the school.
    public List<Student> getStudents() {
        return students;
    }
}
