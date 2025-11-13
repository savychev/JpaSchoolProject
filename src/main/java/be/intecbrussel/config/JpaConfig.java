package be.intecbrussel.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

// This class sets up and shares the JPA entity manager for the whole app.
public class JpaConfig {

    // This factory creates entity managers using the persistence unit named "schooldb".
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("schooldb");

    // This method hands out a new entity manager when the app needs database access.
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // This method closes the factory when the app shuts down to free resources.
    public static void close() {
        emf.close();
    }
}
