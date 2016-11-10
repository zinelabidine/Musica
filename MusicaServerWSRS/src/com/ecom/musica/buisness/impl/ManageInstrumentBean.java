package com.ecom.musica.buisness.impl;

import com.ecom.musica.buisness.ManageInstrumentBeanRemote;
import com.ecom.musica.entities.Instrument;

import java.sql.SQLException;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

/**
 * Session Bean implementation class ManageInstrumentBean
 */
@Stateless
public class ManageInstrumentBean implements ManageInstrumentBeanRemote {

 	@PersistenceContext(unitName = "EntityManagerPU")
    private EntityManager entityManager;	
    /**
     * Default constructor. 
     */
    public ManageInstrumentBean() {
        // TODO Auto-generated constructor stub
}

	@Override
	public boolean addInstrument(Instrument instrument) {
		entityManager.persist(instrument);
		return false;
	}

}
