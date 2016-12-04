package com.ecom.musica.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * Generic abstract JPA Dao that will be extended by every specific Dao
 */
public abstract class AbstractJpaDao<T, IdType extends Serializable> implements Dao<T, IdType> {

	private Class<T> entityClass;

	private EntityManager entityManager;

	/**
	 * Constructor
	 */
	public AbstractJpaDao() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
	}

	/**
	 * Constructor
	 * 
	 * @param clazz
	 */
	public AbstractJpaDao(Class<T> clazz) {
		this.entityClass = clazz;
	}

	/**
	 * Get the current EntityManager
	 * 
	 * @return
	 */
	protected EntityManager getEntityManager() {
		if (this.entityManager == null) {
			throw new IllegalStateException("EntityManager has not been set on DAO before usage");
		}
		return this.entityManager;
	}

	/**
	 * Set the current EntityManager
	 * 
	 * @param em
	 */
	public void setEntityManager(EntityManager em) {
		this.entityManager = em;
	}

	/**
	 * Get the Entity class
	 * 
	 * @return
	 */
	public Class<T> getEntityClass() {
		return this.entityClass;
	}

	/**
	 * Set the Entity class
	 * 
	 * @param entityClass
	 */
	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	/**
	 * Get the Entity name
	 * 
	 * @return
	 */
	public String getEntityName() {
		Entity entity = getEntityClass().getAnnotation(Entity.class);

		String entityName;
		// Entity with specified name
		if ("".equals(entity.name())) {
			entityName = getEntityClass().getName();
			// Entity with default name (Class name)
		} else {
			entityName = entity.name();
		}
		return entityName;
	}

	/**
	 * Analyze the constraint violations.
	 * 
	 * @param exception
	 *            must not be <code>null</code>.
	 */
	private void analyzeConstraintViolations(ConstraintViolationException exception) {
		if (exception.getConstraintViolations().isEmpty()) {
			StringBuilder errorMessage = new StringBuilder();
			for (ConstraintViolation<?> violation : exception.getConstraintViolations()) {
				errorMessage.append("Attribut: ").append(violation.getPropertyPath());
				errorMessage.append(" / Value: ").append(violation.getInvalidValue());
				errorMessage.append(" => ").append(violation.getMessage());
			}
			throw new IllegalStateException("Persitence constraints violation : " + errorMessage);
		}
	}

	/**
	 * 
	 * {@inheritDoc}
	 * 
	 * @see Dao#persist(java.lang.Object)
	 */
	@Override
	public T persist(T transientEntity) {
		try {
			getEntityManager().persist(transientEntity);
		} catch (ConstraintViolationException e) {
			analyzeConstraintViolations(e);
		}
		return transientEntity;
	}

	/**
	 * 
	 * {@inheritDoc}
	 * 
	 * @see Dao#merge(java.lang.Object)
	 */
	@Override
	public T merge(T detachedEntity) {
		try {
			getEntityManager().merge(detachedEntity);
		} catch (ConstraintViolationException e) {
			analyzeConstraintViolations(e);
		}
		return detachedEntity;
	}

	/**
	 * 
	 * {@inheritDoc}
	 * 
	 * @see Dao#remove(java.lang.Object)
	 */
	@Override
	public T remove(T entity) {
		try {
			getEntityManager().remove(entity);
		} catch (ConstraintViolationException e) {
			analyzeConstraintViolations(e);
		}
		return entity;
	}

	/**
	 * 
	 * {@inheritDoc}
	 * 
	 * @see Dao#refresh(java.lang.Object)
	 */
	@Override
	public T refresh(T entity) {
		try {
			getEntityManager().refresh(entity);
		} catch (ConstraintViolationException e) {
			analyzeConstraintViolations(e);
		}
		return entity;
	}

	@Override
	public void detach(T entity) {
		getEntityManager().detach(entity);
	}

	@Override
	public void evict(Class clazz, long id) {
		getEntityManager().getEntityManagerFactory().getCache().evict(clazz, id);
	}

	/**
	 * 
	 * {@inheritDoc}
	 * 
	 * @see Dao#persist(java.util.List)
	 */
	@Override
	public List<T> persist(List<T> entities) {
		for (T entity : entities) {
			persist(entity);
		}
		return entities;
	}

	/**
	 * 
	 * {@inheritDoc}
	 * 
	 * @see Dao#merge(java.util.List)
	 */
	@Override
	public List<T> merge(List<T> entities) {
		for (T entity : entities) {
			merge(entity);
		}
		return entities;
	}

	/**
	 * 
	 * {@inheritDoc}
	 * 
	 * @see Dao#remove(java.util.List)
	 */
	@Override
	public List<T> remove(List<T> entities) {
		for (T entity : entities) {
			remove(entity);
		}
		return entities;
	}

	/**
	 * 
	 * {@inheritDoc}
	 * 
	 * @see Dao#refresh(java.util.List)
	 */
	@Override
	public List<T> refresh(List<T> entities) {
		for (T entity : entities) {
			refresh(entity);
		}
		return entities;
	}

	/**
	 * 
	 * {@inheritDoc}
	 * 
	 * @see Dao#findById(java.io.Serializable)
	 */
	@Override
	public T findById(IdType id) {
		return getEntityManager().find(getEntityClass(), id);
	}

	/**
	 * 
	 * {@inheritDoc}
	 *
	 */
	protected List<T> findBy(String attribute, Object value) {
		String jpqlQuery = "SELECT p FROM " + getEntityName() + " p WHERE " + attribute + "=:objet";
		Query query = getEntityManager().createQuery(jpqlQuery);
		query.setParameter("objet", value);

		return query.getResultList();
	}

	/**
	 * 
	 * {@inheritDoc}
	 *
	 */
	protected List<T> findByList(String attribute, List<?> values) {
		String jpqlQuery = "SELECT p FROM " + getEntityName() + " p WHERE " + attribute + " IN :objects";
		Query query = getEntityManager().createQuery(jpqlQuery);
		query.setParameter("objects", values);

		return query.getResultList();
	}

	/**
	 * 
	 * {@inheritDoc}
	 * 
	 * @see Dao#findAll()
	 */
	@Override
	public List<T> findAll() {
		String jpqlQuery = "select p from " + getEntityName() + " p";
		Query request = getEntityManager().createQuery(jpqlQuery);
		return request.getResultList();
	}

	/**
	 * 
	 * {@inheritDoc}
	 * 
	 * @see Dao#clear()
	 */
	@Override
	public void clear() {
		getEntityManager().clear();
	}

	/**
	 * 
	 * {@inheritDoc}
	 * 
	 * @see Dao#flush()
	 */
	@Override
	public void flush() {
		getEntityManager().setFlushMode(FlushModeType.AUTO);
		getEntityManager().flush();
	}
}
