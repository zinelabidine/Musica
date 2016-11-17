package com.ecom.musica.buisness.impl;

import com.ecom.musica.buisness.ManageInstrumentBeanRemote;
import com.ecom.musica.entities.Instrument;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

	@Override
	public List<Instrument> getAllInstruments() {
		Query req = entityManager.createQuery("select i from Instrument i");
		return req.getResultList();
	}

	@Override
	public List<Instrument> getInstrumentsBestSales() {
		Query req = entityManager.createQuery(
				"select i,count(i) as NbrVente from Instrument i inner join i.commandes group by i.instrumentId order by NbrVente desc");
		List<Instrument> instruments = castResultToList(Instrument.class, 0, req.getResultList());
		return instruments;
	}

	@Override
	public List<Instrument> getInstrumentsPromotion() {
		Query req = entityManager.createQuery("select i  from Instrument i inner join i.promotions");
		List<Instrument> instruments = req.getResultList();
		return instruments;
	}

	// Convertor
	public static <T> List<T> castResultToList(Class<? extends T> c, int position, List<Object[]> dbResult) {
		List<T> castedList = new ArrayList<T>(dbResult.size());
		for (Object[] o : dbResult) {
			castedList.add((T) o[position]);
		}
		return castedList;
	}

	public Instrument getInstrumentWithId(int instrument_id) {
		return entityManager.find(Instrument.class, instrument_id);
	}
}
