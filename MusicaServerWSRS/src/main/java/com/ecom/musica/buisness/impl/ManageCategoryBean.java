package com.ecom.musica.buisness.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ecom.musica.buisness.ManageCategoryBeanRemote;
import com.ecom.musica.entities.Categorie;
import com.ecom.musica.entities.Instrument;

@Stateless
public class ManageCategoryBean implements ManageCategoryBeanRemote {
	@PersistenceContext(unitName = "EntityManagerPU")
	private EntityManager entityManager;
	
	@Override
	public List<Categorie> getAllCategories() {

		Query req = entityManager.createQuery("select c  from Categorie C");
		List<Categorie> instruments = req.getResultList();
		return instruments;
	}

}
