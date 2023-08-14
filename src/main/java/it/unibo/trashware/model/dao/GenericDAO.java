package it.unibo.trashware.model.dao;

import java.util.List;
import java.util.Optional;

/**
 * Interface of a generic DAO (Data Access Object), which interacts directly with the database
 * to perform CRUD operations.
 * @param <T> the entity class that is mapped to the database table accessed by the DAO instance.
 * @param <ID> the type of the entity class ID.
 */
public interface GenericDAO<T, ID> {
    
    /**
     * Gets the row with the passed ID as key value from the table mapped to the entity class {@link T}.
     * @param id
     * @return an {@link Optional} that references an instance of the entity class that carries the data
     * of the searched row if found; an empty {@link Optional} otherwise.
     */
    Optional<T> getByID(ID id);
    
    /**
     * @return all the rows of the table mapped to the entity class {@link T}.
     */
    List<T> getAll();
    
    /**
     * Adds a new row to the database table.
     * @param entity the entity that carries the data of the row to be inserted.
     */
    void add(T entity);
    
    void update(T entity, String[] params);
    
    void delete(T entity);

}
