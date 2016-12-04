package com.ecom.musica.dao.impl;

import javax.ejb.Stateless;

import com.ecom.musica.dao.AbstractMysqlJpaDao;
import com.ecom.musica.dao.UtilisateurDao;
import com.ecom.musica.entities.Utilisateur;

@Stateless
public class UtilisateurDaoImpl extends AbstractMysqlJpaDao<Utilisateur, Integer> implements UtilisateurDao {

}
