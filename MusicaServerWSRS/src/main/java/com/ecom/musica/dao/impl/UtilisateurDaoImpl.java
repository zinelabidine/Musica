package com.ecom.musica.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.ecom.musica.dao.AbstractMysqlJpaDao;
import com.ecom.musica.dao.UtilisateurDao;
import com.ecom.musica.entities.Profil;
import com.ecom.musica.entities.Utilisateur;

@Stateless
public class UtilisateurDaoImpl extends AbstractMysqlJpaDao<Utilisateur, Integer> implements UtilisateurDao {

	@Override
	public Utilisateur getUtilisateurByLogin(String login) {
		String jpqlQuery = "SELECT p FROM " + getEntityName() + " p WHERE p.login = '" + login + "'";
		Query query = getEntityManager().createQuery(jpqlQuery);

		List<Utilisateur> utilisateurs = query.getResultList();
		if (utilisateurs.size() == 1) {
			return utilisateurs.get(0);
		} else {
			return null;
		}
	}
}
