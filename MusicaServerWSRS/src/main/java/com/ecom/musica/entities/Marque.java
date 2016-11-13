package com.ecom.musica.entities;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Marque")
public class Marque {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MarqueId")
	private int marqueId;
	
	@Column(name = "Libelle")
	private String libelle;

	@OneToMany(mappedBy="marque")
	private List<Instrument> instruments;
	
	@ManyToMany(mappedBy = "marques")
	private List<Promotion> promotions;
}
