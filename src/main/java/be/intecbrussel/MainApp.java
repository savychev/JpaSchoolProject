package be.intecbrussel;

import be.intecbrussel.model.Student;
import be.intecbrussel.config.JpaConfig;
import jakarta.persistence.EntityManager;

public class MainApp {
    public static void main(String[] args) {
        EntityManager em = null;
        try {
            em = JpaConfig.getEntityManager();
            em.getTransaction().begin();

            Student s = new Student("Anna", "Ivanova"); // 1
            em.persist(s);                               // 2

            em.getTransaction().commit();
            System.out.println("✅ Saved student with id=" + s.getId()); // 3
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) em.close();
            JpaConfig.close();
        }
    }
}
