package com.ecom.musica.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Instrument")
public class Instrument {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "InstrumentId")
	private int instrumentId;

	@ManyToOne
	@JoinColumn(name = "MarqueId")
	private Marque marque;

	@ManyToOne
	@JoinColumn(name = "CategorieId")
	private Categorie categorie;

	@ManyToMany(mappedBy = "instruments")
	private List<Commande> commandes;
	
	@ManyToMany(mappedBy = "instruments")
	private List<Panier> paniers;
	
	@ManyToMany(mappedBy = "instruments")
	private List<Musicien> musiciens;
	
	@ManyToMany(mappedBy = "instruments")
	private List<Promotion> promotions;

	@Column(name = "Reference")
	private String reference;

	@Column(name = "Quantite")
	private int quantite;

	// private IMAGE VARCHAR(1000) NOT NULL ,
	@Column(name = "Prix")
	private float prix;

	// getter and setters

	

}
