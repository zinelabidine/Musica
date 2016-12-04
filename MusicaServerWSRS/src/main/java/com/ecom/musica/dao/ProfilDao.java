package com.ecom.musica.dao;

import com.ecom.musica.entities.Profil;

public interface ProfilDao extends Dao<Profil, Integer> {
	public Profil findByLibelle(String libelle);

}