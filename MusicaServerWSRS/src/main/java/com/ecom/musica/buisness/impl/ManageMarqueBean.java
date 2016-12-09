package com.ecom.musica.buisness.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ecom.musica.buisness.ManageMarqueBeanRemote;
import com.ecom.musica.entities.Marque;

@Stateless
public class ManageMarqueBean implements ManageMarqueBeanRemote {
	@PersistenceContext(unitName = "EntityManagerPU")
	private EntityManager entityManager;
	
	@Override
	public List<Marque> getAllMarques() {
		Query req = entityManager.createQuery("select distinct m from Marque M");
		List<Marque> marques = req.getResultList();
		return marques;
	}
	
	public int getMarqueId(String marqueLibelle) throws Exception {
		Query req = entityManager.createQuery("select m "
											+ "from Marque m "
											+ "where m.libelle like '%" + marqueLibelle + "%'");
		List<Marque> marques = req.getResultList();
		Marque marque = null;
		if(!marques.isEmpty()){
		    // ignores multiple results
			marque = marques.get(0);
		}
		else{
			marque = new Marque(marqueLibelle);
		}
		try {
            entityManager.persist(marque);
        } catch (Exception e) {
            throw new Exception("la marque n'a pas pu etre inseré");
        }
		
		return marque.getMarqueId();
	}

}
