package it.unibo.trashware.model.dao;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibo.trashware.model.provider.ConnectionProvider;
import it.unibo.trashware.model.provider.ConnectionProviderImpl;
import jakarta.persistence.EntityManager;

public class GenericDAOImpl<T, ID> implements GenericDAO<T, ID> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenericDAOImpl.class);

    private EntityManager em;

    public GenericDAOImpl() throws IOException {
        // Connect to the database
        final ConnectionProvider provider = new ConnectionProviderImpl();
        final Optional<EntityManager> response = provider.getConnection();
        if (response.isEmpty()) {
            throw new IOException("Error: DAO could not establish a connection with the database.");
        }
        this.em = response.get();
    }

    @Override
    public Optional<T> getByID(ID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByID'");
    }

    @Override
    public List<T> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public void add(T entity) {
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
    public void update(T entity, String[] params) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(T entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}