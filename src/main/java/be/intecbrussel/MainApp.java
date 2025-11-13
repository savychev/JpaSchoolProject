package be.intecbrussel;

import be.intecbrussel.config.JpaConfig;
import be.intecbrussel.model.School;
import be.intecbrussel.model.Student;
import be.intecbrussel.model.Teacher;
import be.intecbrussel.service.SchoolService;
import be.intecbrussel.service.StudentService;
import be.intecbrussel.service.TeacherService;

public class MainApp {
    public static void main(String[] args) {
        try {
            SchoolService schoolService = new SchoolService();
            StudentService studentService = new StudentService();
            TeacherService teacherService = new TeacherService();

            // -----------------------------
            // 1. Создаём школу
            // -----------------------------
            School school = schoolService.addSchool("Intec Brussel", "Brussels");
            System.out.println("🏫 Created school: id=" + school.getId());

            // -----------------------------
            // 2. Создаём студентов
            // -----------------------------
            Student anna = studentService.addStudent("Anna", "Ivanova");
            Student bob  = studentService.addStudent("Bob", "Petrov");

            System.out.println("👩‍🎓 Created students: " + anna.getId() + ", " + bob.getId());

            // -----------------------------
            // 3. Связываем студентов со школой
            // -----------------------------
            schoolService.addStudentToSchool(school.getId(), anna.getId());
            schoolService.addStudentToSchool(school.getId(), bob.getId());

            System.out.println("📌 Students assigned to school.");

            // -----------------------------
            // 4. Создаём учителей
            // -----------------------------
            Teacher alice = teacherService.addTeacher("Alice", "Brown");
            Teacher john  = teacherService.addTeacher("John", "Smith");

            System.out.println("👨‍🏫 Created teachers: " + alice.getId() + ", " + john.getId());

            // -----------------------------
            // 5. Привязываем учителей к школе
            // -----------------------------
            teacherService.assignTeacherToSchool(alice.getId(), school.getId());
            teacherService.assignTeacherToSchool(john.getId(), school.getId());

            System.out.println("🏫 Teachers assigned to school.");

            // -----------------------------
            // 6. Many-to-Many связи: учителя ↔ студенты
            // -----------------------------
            teacherService.addStudentToTeacher(alice.getId(), anna.getId()); // Alice учит Анну
            teacherService.addStudentToTeacher(john.getId(), anna.getId());  // John учит Анну
            teacherService.addStudentToTeacher(john.getId(), bob.getId());   // John учит Боба

            System.out.println("🔗 Teachers linked with students.");

            // -----------------------------
            // Готово
            // -----------------------------
            System.out.println("\n🎉 All operations completed successfully!");
        } finally {
            JpaConfig.close();
        }
    }
}
