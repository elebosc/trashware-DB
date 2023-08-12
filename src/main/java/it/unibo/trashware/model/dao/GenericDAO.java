package it.unibo.trashware.model.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDAO<T, ID> {
    
    Optional<T> getByID(ID id);
    
    List<T> getAll();
    
    void add(T entity);
    
    void update(T entity, String[] params);
    
    void delete(T entity);

}
