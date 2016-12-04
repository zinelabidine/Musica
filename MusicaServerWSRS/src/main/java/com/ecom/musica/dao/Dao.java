package com.ecom.musica.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Generic implementation of Dao that will be implemented by every specific Dao
 * 
 */
public interface Dao<T, IdType extends Serializable> {

	/**
	 * Persist the instance.
	 * 
	 * @param transientEntity
	 *            must not be <code>null</code>.
	 * @return the instance.
	 */
	T persist(T transientEntity);

	/**
	 * Merge the instance.
	 * 
	 * @param detachedEntity
	 *            must not be <code>null</code>.
	 * @return the instance.
	 */
	T merge(T detachedEntity);

	/**
	 * Deletes the instance.
	 * 
	 * @param entity
	 *            must not be <code>null</code>.
	 * @return the instance.
	 */
	T remove(T entity);

	/**
	 * Refresh the instance: update the entity object with values taken from the
	 * database
	 * 
	 * @param entity
	 *            must not be <code>null</code>.
	 * @return the instance.
	 */
	T refresh(T entity);

	/**
	 * Detach the entity from em
	 *
	 * @param entity
	 */
	void detach(T entity);

	/**
	 * Evict the entity from em
	 *
	 */
	void evict(Class clazz, long id);

	/**
	 * Persist the instance.
	 * 
	 * @param entities
	 *            must not be <code>null</code>.
	 * @return the list of instance.
	 */
	List<T> persist(List<T> entities);

	/**
	 * Merge the instance.
	 * 
	 * @param entities
	 *            must not be <code>null</code>.
	 * @return the list of instance.
	 */
	List<T> merge(List<T> entities);

	/**
	 * Deletes the instance.
	 * 
	 * @param entities
	 *            must not be <code>null</code>.
	 * @return the list of instance.
	 */
	List<T> remove(List<T> entities);

	/**
	 * Refresh the instance: update the entity object with values taken from the
	 * database
	 * 
	 * @param entities
	 *            must not be <code>null</code>.
	 * @return the list of instance.
	 */
	List<T> refresh(List<T> entities);

	/**
	 * Finds the instance with the given id.
	 * 
	 * @param id
	 *            must not be <code>null</code>.
	 * @return the instance, or <code>null</code>.
	 */
	T findById(IdType id);

	/**
	 * Finds all instances.
	 * 
	 * @return never <code>null</code> but rather an empty list.
	 */
	List<T> findAll();

	/**
	 * Clears the persistence context.
	 */
	void clear();

	/**
	 * Flush the persistence context.
	 */
	void flush();

}
