package be.intecbrussel;

import be.intecbrussel.config.JpaConfig;
import be.intecbrussel.model.School;
import be.intecbrussel.model.Student;
import jakarta.persistence.EntityManager;

public class MainApp {
    public static void main(String[] args) {
        EntityManager em = JpaConfig.getEntityManager();
        em.getTransaction().begin();

        School school = new School("Intec Brussel", "Brussels");
        Student s1 = new Student("Anna", "Ivanova");
        Student s2 = new Student("Bob", "Petrov");

        // связываем студентов со школой
        school.addStudent(s1);
        school.addStudent(s2);

        // сохраняем школу (из-за cascade сохранит и студентов)
        em.persist(school);

        em.getTransaction().commit();
        em.close();
        JpaConfig.close();

        System.out.println("✅ School and students saved!");
    }
}
