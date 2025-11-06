package be.intecbrussel;

import be.intecbrussel.config.JpaConfig;
import jakarta.persistence.EntityManager;

public class MainApp {
    public static void main(String[] args) {
        EntityManager em = null;
        try {
            em = JpaConfig.getEntityManager();
            System.out.println("✅ JPA подключена к MySQL успешно!");
        } catch (Exception e) {
            System.err.println("❌ Ошибка подключения: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) em.close();
            JpaConfig.close();
        }
    }
}
