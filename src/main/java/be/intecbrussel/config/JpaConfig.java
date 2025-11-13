package be.intecbrussel.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.HashMap;
import java.util.Map;

public class JpaConfig {
    private static final EntityManagerFactory emf = buildEntityManagerFactory();

    private static EntityManagerFactory buildEntityManagerFactory() {
        Map<String, String> overrides = new HashMap<>();
        putIfPresent(overrides, "jakarta.persistence.jdbc.url", lookup("DB_URL"));
        putIfPresent(overrides, "jakarta.persistence.jdbc.user", lookup("DB_USER"));
        putIfPresent(overrides, "jakarta.persistence.jdbc.password", lookup("DB_PASSWORD"));

        if (overrides.isEmpty()) {
            return Persistence.createEntityManagerFactory("schooldb");
        }

        return Persistence.createEntityManagerFactory("schooldb", overrides);
    }

    private static void putIfPresent(Map<String, String> overrides, String key, String value) {
        if (value != null && !value.isBlank()) {
            overrides.put(key, value);
        }
    }

    private static String lookup(String key) {
        String envValue = System.getenv(key);
        if (envValue != null && !envValue.isBlank()) {
            return envValue;
        }
        return System.getProperty(key);
    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void close() {
        emf.close();
    }
}
