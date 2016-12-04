package com.ecom.musica.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Generic abstract JPA Dao that will be extended by every specific Dao
 */
public abstract class AbstractMysqlJpaDao<T, IdType extends Serializable> extends AbstractJpaDao<T, IdType> {

	/**
	 * Set the current EntityManager. Override to select the correct persistence
	 * context.
	 * 
	 * @param em
	 *            {@link EntityManager}
	 */
	@Override
	@PersistenceContext(unitName = "EntityManagerPU")
	public void setEntityManager(EntityManager em) {
		super.setEntityManager(em);
	}

}
