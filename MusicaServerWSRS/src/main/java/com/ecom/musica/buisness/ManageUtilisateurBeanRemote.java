package com.ecom.musica.buisness;

import javax.ejb.Remote;

import com.ecom.musica.entities.Utilisateur;

@Remote
public interface ManageUtilisateurBeanRemote {
	public Utilisateur registerUtilisateur(Utilisateur utilisateur);

	public Utilisateur getUtilisateurInformationds(int IdUtilisateur);
}
