package com.ecom.musica.buisness.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ecom.musica.buisness.ManageCategoryBeanRemote;
import com.ecom.musica.entities.Categorie;
import com.ecom.musica.entities.Instrument;
import com.ecom.musica.entities.Marque;

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

	public int getCategorieId(String categorieLibelle) throws Exception {
		Query req = entityManager.createQuery("select c "
											+ "from Categorie c "
											+ "where c.libelle like '%" + categorieLibelle + "%'");
		List<Categorie> categories = req.getResultList();
		Categorie categorie = null;
		if(!categories.isEmpty()){
		    // ignores multiple results
			categorie = categories.get(0);
		}
		else{
			categorie = new Categorie(categorieLibelle);
		}
		try {
            entityManager.persist(categorie);
        } catch (Exception e) {
            throw new Exception("la marque n'a pas pu etre inseré");
        }
		
		return categorie.getCategorieId();
		
	}
}
