package com.ecom.musica.entities;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Categorie")
public class Categorie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CategorieId")
	private int categorieId;
	
	@Column(name = "Libelle")
	private String libelle;
	
	@OneToMany(mappedBy="categorie")
	private List<Instrument> instruments;
}
