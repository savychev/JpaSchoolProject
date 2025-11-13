package be.intecbrussel;

import be.intecbrussel.config.JpaConfig;
import be.intecbrussel.model.School;
import be.intecbrussel.model.Student;
import be.intecbrussel.model.Teacher;
import be.intecbrussel.repository.SchoolRepository;
import be.intecbrussel.repository.StudentRepository;
import be.intecbrussel.repository.TeacherRepository;
import be.intecbrussel.service.SchoolService;
import be.intecbrussel.service.StudentService;
import be.intecbrussel.service.TeacherService;
import jakarta.persistence.EntityManager;

import java.util.List;

public class MainApp2 {

    public static void main(String[] args) {

        EntityManager em = JpaConfig.getEntityManager();


        SchoolRepository schoolRepo = new SchoolRepository(em);
        StudentRepository studentRepo = new StudentRepository(em);
        TeacherRepository teacherRepo = new TeacherRepository(em);


        SchoolService schoolService = new SchoolService(schoolRepo);
        StudentService studentService = new StudentService(studentRepo);
        TeacherService teacherService = new TeacherService(teacherRepo);

        School schoolA = new School();
        schoolA.setName("Alpha School");
        schoolA.setCity("Brussels");
        schoolService.addSchool(schoolA);

        School schoolB = new School();
        schoolB.setName("Beta School");
        schoolB.setCity("Antwerp");
        schoolService.addSchool(schoolB);


        Teacher t1 = new Teacher();
        t1.setFirstname("John");
        t1.setLastname("Doe");
        t1.setSchool(schoolA);

        Teacher t2 = new Teacher();
        t2.setFirstname("Sarah");
        t2.setLastname("Smith");
        t2.setSchool(schoolA);

        Teacher t3 = new Teacher();
        t3.setFirstname("Bob");
        t3.setLastname("Brown");
        t3.setSchool(schoolB);

        teacherService.addTeacher(t1);
        teacherService.addTeacher(t2);
        teacherService.addTeacher(t3);


        Student s1 = new Student();
        s1.setFirstname("Alex");
        s1.setLastname("Johnson");
        s1.setSchool(schoolA);

        Student s2 = new Student();
        s2.setFirstname("Emily");
        s2.setLastname("Davis");
        s2.setSchool(schoolA);

        Student s3 = new Student();
        s3.setFirstname("Michael");
        s3.setLastname("Wilson");
        s3.setSchool(schoolB);


        s1.getTeachers().add(t1);
        t1.getStudents().add(s1);

        s1.getTeachers().add(t2);
        t2.getStudents().add(s1);


        s2.getTeachers().add(t2);
        t2.getStudents().add(s2);


        s3.getTeachers().add(t3);
        t3.getStudents().add(s3);

        studentService.addStudent(s1);
        studentService.addStudent(s2);
        studentService.addStudent(s3);


        System.out.println("=== Schools ===");
        for (School sc : schoolService.getAllSchools()) {
            System.out.println("- " + sc.getName() + " (" + sc.getCity() + ")");
        }

        System.out.println("\n=== Teachers ===");
        for (Teacher t : teacherService.getAllTeachers()) {
            System.out.println("- " + t.getFirstname() + " " + t.getLastname() +
                    " | School: " + t.getSchool().getName());
        }

        System.out.println("\n=== Students ===");
        for (Student st : studentService.getAllStudents()) {
            System.out.print("- " + st.getFirstname() + " " + st.getLastname() +
                    " | School: " + st.getSchool().getName() +
                    " | Teachers: ");

            for (Teacher teacher : st.getTeachers()) {
                System.out.print(teacher.getFirstname() + " ");
            }
            System.out.println();
        }

        em.close();
        JpaConfig.close();
    }
}
