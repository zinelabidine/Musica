package com.ecom.musica.buisness.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.mindrot.jbcrypt.BCrypt;

import com.ecom.musica.buisness.ManageUtilisateurBeanRemote;
import com.ecom.musica.dao.UtilisateurDao;
import com.ecom.musica.entities.Utilisateur;

@Stateless
public class ManageUtilisateurBean implements ManageUtilisateurBeanRemote {

	@EJB
	private UtilisateurDao utilisateurDao;

	@Override
	public Utilisateur getUtilisateurInformationds(int IdUtilisateur) {
		return utilisateurDao.findById(IdUtilisateur);
	}

	@Override
	public Utilisateur registerUtilisateur(Utilisateur utilisateur) {
		String cryptedPW = BCrypt.hashpw(utilisateur.getMdp(), BCrypt.gensalt());
		utilisateur.setMdp(cryptedPW);
		return utilisateurDao.persist(utilisateur);
	}

	@Override
	public Utilisateur getUtilisateurByLogin(String login) {
		return utilisateurDao.getUtilisateurByLogin(login);
	}

}
