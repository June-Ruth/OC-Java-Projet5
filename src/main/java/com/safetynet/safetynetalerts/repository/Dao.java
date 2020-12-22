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
     * @param t object concerned
     * @return
     */
    T save(T t);

    /**
     * Change parameters of T.
     * @param t object concerned
     * @return
     */
    boolean update(T t);

    /**
     * Delete an object T.
     * @param t object concerned
     * @return
     */
    boolean delete(T t);

}
