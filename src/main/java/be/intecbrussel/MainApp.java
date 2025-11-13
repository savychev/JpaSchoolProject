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

// This main class shows how to use the services to manage school data.
public class MainApp {

    // This method runs the sample steps that create, read, update, and delete data.
    public static void main(String[] args) {

        // This entity manager opens a link to the database for all following actions.
        EntityManager entityManager = JpaConfig.getEntityManager();

        // These repositories wrap direct database work for schools, students, and teachers.
        SchoolRepository schoolRepository = new SchoolRepository(entityManager);
        StudentRepository studentRepository = new StudentRepository(entityManager);
        TeacherRepository teacherRepository = new TeacherRepository(entityManager);

        // These services use the repositories and provide easier methods for the app.
        SchoolService schoolService = new SchoolService(schoolRepository);
        StudentService studentService = new StudentService(studentRepository);
        TeacherService teacherService = new TeacherService(teacherRepository);

        // This school object is created and saved so other records can link to it.
        School school = new School();
        school.setName("Intec School");
        school.setCity("Brussels");
        schoolService.addSchool(school);

        // These teacher objects are filled with data and linked to the school.
        Teacher teacher1 = new Teacher();
        teacher1.setFirstname("Jan");
        teacher1.setLastname("Jansen");
        teacher1.setSchool(school);

        Teacher teacher2 = new Teacher();
        teacher2.setFirstname("Piet");
        teacher2.setLastname("Peeters");
        teacher2.setSchool(school);

        // The teachers are stored through the teacher service.
        teacherService.addTeacher(teacher1);
        teacherService.addTeacher(teacher2);

        // These student objects are created, linked to the school, and later saved.
        Student student1 = new Student();
        student1.setFirstname("Anna");
        student1.setLastname("Novak");
        student1.setSchool(school);

        Student student2 = new Student();
        student2.setFirstname("Maria");
        student2.setLastname("Ivanova");
        student2.setSchool(school);

        // These lines connect students and teachers to show their many-to-many relation.
        student1.getTeachers().add(teacher1);
        student1.getTeachers().add(teacher2);
        teacher1.getStudents().add(student1);
        teacher2.getStudents().add(student1);

        student2.getTeachers().add(teacher1);
        teacher1.getStudents().add(student2);

        // The students are stored with their linked teachers and school.
        studentService.addStudent(student1);
        studentService.addStudent(student2);

        // This block reads and prints all schools to the console.
        List<School> schools = schoolService.getAllSchools();
        System.out.println("Schools:");
        for (School s : schools) {
            System.out.println("- " + s.getName() + " (" + s.getCity() + ")");
        }

        // This block reads and prints all teachers to the console.
        List<Teacher> teachers = teacherService.getAllTeachers();
        System.out.println("Teachers:");
        for (Teacher t : teachers) {
            System.out.println("- " + t.getFirstname() + " " + t.getLastname());
        }

        // This block reads and prints all students to the console.
        List<Student> students = studentService.getAllStudents();
        System.out.println("Students:");
        for (Student st : students) {
            System.out.println("- " + st.getFirstname() + " " + st.getLastname());
        }

        // This student carries new names that will replace the old data.
        Student updatedStudentData = new Student();
        updatedStudentData.setFirstname("Anna-Updated");
        updatedStudentData.setLastname("Novak-Updated");
        updatedStudentData.setSchool(school);

        // This call updates the first student with the new information.
        studentService.updateStudent(student1.getId(), updatedStudentData);

        // This block prints all students after the update to show the change.
        System.out.println("After update:");
        students = studentService.getAllStudents();
        for (Student st : students) {
            System.out.println("- " + st.getId() + ": " + st.getFirstname() + " " + st.getLastname());
        }

        // This block removes the first student if one exists and shows which was deleted.
        if (!students.isEmpty()) {
            Long idToDelete = students.get(0).getId();
            studentService.removeStudent(idToDelete);
            System.out.println("Deleted student with id: " + idToDelete);
        }

        // This block prints all students after the delete to confirm the removal.
        System.out.println("After delete:");
        students = studentService.getAllStudents();
        for (Student st : students) {
            System.out.println("- " + st.getId() + ": " + st.getFirstname() + " " + st.getLastname());
        }

        // These calls close the JPA resources to avoid leaving open connections.
        JpaConfig.close();
        entityManager.close();
    }
}
