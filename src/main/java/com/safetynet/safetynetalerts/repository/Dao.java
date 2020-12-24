package com.safetynet.safetynetalerts.repository;

import java.util.Set;

public interface Dao<T> {

    /**
     * Find All Object T that Implements Dao
     * @return List of T object
     */
    Set<T> findAll();

    /**
     * Create and save an Object T.
     * @param t object concerned
     * @return true if saved
     */
    boolean save(T t);

    /**
     * Change parameters of T.
     * @param t object concerned
     * @return true if updated
     */
    boolean update(T t);

    /**
     * Delete an object T.
     * @param t object concerned
     * @return true if deleted
     */
    boolean delete(T t);

}
