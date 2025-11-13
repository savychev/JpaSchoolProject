package be.intecbrussel.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Utility class that encapsulates the repetitive boilerplate required to
 * obtain an {@link EntityManager}, manage transactions and make sure the
 * resources are properly released.
 *
 * <p>The repositories previously duplicated the same patterns of
 * "create manager → begin transaction → commit/rollback → close". By moving
 * that logic to a single helper we decrease the amount of duplicated code and
 * make the intent of the repository methods clearer.</p>
 */
public final class JpaExecutor {

    private JpaExecutor() {
        // Utility class
    }

    /**
     * Executes the provided unit of work with a managed {@link EntityManager}
     * without starting a transaction. Suitable for read-only operations.
     */
    public static <T> T execute(Function<EntityManager, T> work) {
        EntityManager entityManager = JpaConfig.getEntityManager();
        try {
            return work.apply(entityManager);
        } finally {
            entityManager.close();
        }
    }

    /**
     * Variant of {@link #execute(Function)} for side-effect actions.
     */
    public static void execute(Consumer<EntityManager> work) {
        execute(entityManager -> {
            work.accept(entityManager);
            return null;
        });
    }

    /**
     * Executes the provided unit of work within a transaction. The transaction
     * is committed on success and rolled back if an exception is thrown.
     */
    public static <T> T executeInTransaction(Function<EntityManager, T> work) {
        EntityManager entityManager = JpaConfig.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            T result = work.apply(entityManager);
            transaction.commit();
            return result;
        } catch (RuntimeException exception) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw exception;
        } finally {
            entityManager.close();
        }
    }

    /**
     * Variant of {@link #executeInTransaction(Function)} for side-effect
     * actions.
     */
    public static void executeInTransaction(Consumer<EntityManager> work) {
        executeInTransaction(entityManager -> {
            work.accept(entityManager);
            return null;
        });
    }
}

