package com.ecom.musica.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.ecom.musica.dao.AbstractMysqlJpaDao;
import com.ecom.musica.dao.ProfilDao;
import com.ecom.musica.entities.Profil;

@Stateless
public class ProfilDaoImpl extends AbstractMysqlJpaDao<Profil, Integer> implements ProfilDao {

	@Override
	public Profil findByLibelle(String libelle) {
		String jpqlQuery = "SELECT p FROM Profil p WHERE p.libelle = 'ADMIN'";
		Query query = getEntityManager().createQuery(jpqlQuery);

		List<Profil> profils = query.getResultList();
		if (profils.size() == 1) {
			return profils.get(0);
		} else {
			return null;
		}
	}
}
