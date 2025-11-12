package be.intecbrussel;

import be.intecbrussel.config.JpaConfig;
import be.intecbrussel.model.School;
import be.intecbrussel.model.Student;
import be.intecbrussel.model.Teacher;
import jakarta.persistence.EntityManager;

public class MainApp {
    public static void main(String[] args) {
        EntityManager em = JpaConfig.getEntityManager();
        try {
            em.getTransaction().begin();

            // 1) Читаем существующую школу и студентов
            School school = em.createQuery("SELECT s FROM School s", School.class)
                    .setMaxResults(1).getSingleResult();

            Student s1 = em.createQuery("SELECT s FROM Student s WHERE s.firstname = :fn", Student.class)
                    .setParameter("fn", "Anna")
                    .setMaxResults(1).getSingleResult();

            Student s2 = em.createQuery("SELECT s FROM Student s WHERE s.firstname = :fn", Student.class)
                    .setParameter("fn", "Bob")
                    .setMaxResults(1).getSingleResult();

            // 2) Создаём нового учителя и привязываем его к школе
            Teacher t = new Teacher("Alice", "Brown");
            t.setSchool(school);

            // 3) Связываем учителя со студентами (many-to-many)
            t.addStudent(s1);
            t.addStudent(s2);

            // 4) Сохраняем только учителя — всё остальное уже есть
            em.persist(t);

            em.getTransaction().commit();
            System.out.println("✅ Teacher saved: id=" + t.getId());
        } finally {
            em.close();
            JpaConfig.close();
        }
    }
}