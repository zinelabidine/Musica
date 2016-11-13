package com.ecom.musica.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Promotion")
public class Promotion implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PromotionId")
	private int promotionId;
	
	@Column(name = "Libelle")
	private String libelle;
	
	@Column(name = "dateDebut")
	private Date dateDebut;
	
	@Column(name = "DateFin")
	private Date dateFin;
	
	@Column(name = "Taux")
	private float taux;
	
	@ManyToMany
	@JoinTable(name = "InstrumentPromotion", joinColumns = @JoinColumn(name = "PromotionId", referencedColumnName = "PromotionId"), inverseJoinColumns = @JoinColumn(name = "InstrumentId", referencedColumnName = "InstrumentId"))
	private List<Instrument> instruments;
	
	@ManyToMany
	@JoinTable(name = "MarquePromotion", joinColumns = @JoinColumn(name = "PromotionId", referencedColumnName = "PromotionId"), inverseJoinColumns = @JoinColumn(name = "MarqueId", referencedColumnName = "MarqueId"))
	private List<Marque> marques;
}
