package it.unibo.trashware.model.dao;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibo.trashware.model.provider.ConnectionProvider;
import it.unibo.trashware.model.provider.ConnectionProviderImpl;
import jakarta.persistence.EntityManager;

/**
 * {@link GenericDAO} implementation.
 */
public class GenericDAOImpl<T, ID> implements GenericDAO<T, ID> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenericDAOImpl.class);

    private EntityManager em;
    private Class<T> entityClass;

    /**
     * Creates a new instance of a generic DAO.
     * @throws IOException if an error occurs while trying to create a connection to the database.
     */
    public GenericDAOImpl(final Class<T> entityClass) throws IOException {
        // Connect to the database
        final ConnectionProvider provider = new ConnectionProviderImpl();
        final Optional<EntityManager> response = provider.getConnection();
        if (response.isEmpty()) {
            throw new IOException("Error: DAO could not establish a connection with the database.");
        }
        this.em = response.get();
        // Set entity class
        this.entityClass = entityClass;
    }

    @Override
    public Optional<T> getByID(final ID id) {
        final T entity = this.em.find(this.entityClass, id);
        return (entity != null) ? Optional.of(entity) : Optional.empty();
    }

    @Override
    public List<T> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public void add(final T entity) {
        em.getTransaction().begin();
        try {
            em.persist(entity);
            em.getTransaction().commit();
        } catch (final Exception ex) {
            LOGGER.error("Error: insert operation failed.", ex);
            em.getTransaction().rollback();
            throw ex;
        }
    }

    @Override
    public T update(final T entity) {
        final T updatedEntity;
        em.getTransaction().begin();
        try {
            updatedEntity = em.merge(entity);
            em.getTransaction().commit();
        } catch (final Exception ex) {
            LOGGER.error("Error: update operation failed.", ex);
            em.getTransaction().rollback();
            throw ex;
        }
        return updatedEntity;
    }

    @Override
    public void delete(final T entity) {
        em.getTransaction().begin();
        try {
            em.remove(entity);
            em.getTransaction().commit();
        } catch (final Exception ex) {
            LOGGER.error("Error: delete operation failed.", ex);
            em.getTransaction().rollback();
            throw ex;
        }
    }

}