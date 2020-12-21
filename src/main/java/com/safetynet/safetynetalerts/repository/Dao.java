package com.safetynet.safetynetalerts.repository;

import java.util.List;

public interface Dao<T> {

    /**
     * Find All Object T that Implements Dao
     * @return List of T object
     */
    List<T> findAll();

    /**
     * Create and save an Object T.
     * @param t depends of equals() method of T
     */
    void save(T t);

    /**
     * Change parameters of T.
     * @param t depends of equals() method of T
     */
    void update(T t);

    /**
     * Delete an object T.
     * @param t depends of equals() method of T
     */
    void delete(T t);

}
