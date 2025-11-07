package be.intecbrussel.model;                // 1

import jakarta.persistence.*;                 // 2

@Entity                                      // 3
@Table(name = "students")                    // 4
public class Student {                       // 5
    @Id                                      // 6
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 7
    private Long id;                         // 8

    private String firstname;                // 9
    private String lastname;                 // 10

    public Student() {
    }                      // 11

    public Student(String firstname, String lastname) { // 12
        this.firstname = firstname;
        this.lastname = lastname;
    }

    // getters/setters                       // 13
    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}

@ManyToOne(fetch = FetchType.LAZY)          // у каждого студента одна школа
@JoinColumn(name = "school_id")             // внешний ключ в таблице students
private School school;

public School getSchool() {
    return school;
}

public void setSchool(School school) {
    this.school = school;
}
