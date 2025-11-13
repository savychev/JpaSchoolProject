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

public class MainApp {

    public static void main(String[] args) {

        EntityManager entityManager = JpaConfig.getEntityManager();

        SchoolRepository schoolRepository = new SchoolRepository(entityManager);
        StudentRepository studentRepository = new StudentRepository(entityManager);
        TeacherRepository teacherRepository = new TeacherRepository(entityManager);

        SchoolService schoolService = new SchoolService(schoolRepository);
        StudentService studentService = new StudentService(studentRepository);
        TeacherService teacherService = new TeacherService(teacherRepository);

        School school = new School();
        school.setName("Intec School");
        school.setCity("Brussels");
        schoolService.addSchool(school);

        Teacher teacher1 = new Teacher();
        teacher1.setFirstname("Jan");
        teacher1.setLastname("Jansen");
        teacher1.setSchool(school);

        Teacher teacher2 = new Teacher();
        teacher2.setFirstname("Piet");
        teacher2.setLastname("Peeters");
        teacher2.setSchool(school);

        teacherService.addTeacher(teacher1);
        teacherService.addTeacher(teacher2);

        Student student1 = new Student();
        student1.setFirstname("Anna");
        student1.setLastname("Novak");
        student1.setSchool(school);

        Student student2 = new Student();
        student2.setFirstname("Maria");
        student2.setLastname("Ivanova");
        student2.setSchool(school);

        student1.getTeachers().add(teacher1);
        student1.getTeachers().add(teacher2);
        teacher1.getStudents().add(student1);
        teacher2.getStudents().add(student1);

        student2.getTeachers().add(teacher1);
        teacher1.getStudents().add(student2);

        studentService.addStudent(student1);
        studentService.addStudent(student2);

        List<School> schools = schoolService.getAllSchools();
        System.out.println("Schools:");
        for (School s : schools) {
            System.out.println("- " + s.getName() + " (" + s.getCity() + ")");
        }

        List<Teacher> teachers = teacherService.getAllTeachers();
        System.out.println("Teachers:");
        for (Teacher t : teachers) {
            System.out.println("- " + t.getFirstname() + " " + t.getLastname());
        }

        List<Student> students = studentService.getAllStudents();
        System.out.println("Students:");
        for (Student st : students) {
            System.out.println("- " + st.getFirstname() + " " + st.getLastname());
        }

        Student updatedStudentData = new Student();
        updatedStudentData.setFirstname("Anna-Updated");
        updatedStudentData.setLastname("Novak-Updated");
        updatedStudentData.setSchool(school);

        studentService.updateStudent(student1.getId(), updatedStudentData);

        System.out.println("After update:");
        students = studentService.getAllStudents();
        for (Student st : students) {
            System.out.println("- " + st.getId() + ": " + st.getFirstname() + " " + st.getLastname());
        }

        if (!students.isEmpty()) {
            Long idToDelete = students.get(0).getId();
            studentService.removeStudent(idToDelete);
            System.out.println("Deleted student with id: " + idToDelete);
        }

        System.out.println("After delete:");
        students = studentService.getAllStudents();
        for (Student st : students) {
            System.out.println("- " + st.getId() + ": " + st.getFirstname() + " " + st.getLastname());
        }

        JpaConfig.close();
        entityManager.close();
    }
}
