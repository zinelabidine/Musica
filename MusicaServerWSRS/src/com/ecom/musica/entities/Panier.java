package com.ecom.musica.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Panier")
public class Panier {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PanierId")
	private int panierId;
	
	@ManyToOne
	@JoinColumn(name = "ClientId")
	private Client client;
	
	@Column(name = "MontantTTC")
	private float montantTTC;
	
	@Column(name = "MontantHT")
	private float montantHT;
	
	@ManyToMany
	@JoinTable(name = "PanierInstrument", joinColumns = @JoinColumn(name = "PanierId", referencedColumnName = "PanierId"), inverseJoinColumns = @JoinColumn(name = "InstrumentId", referencedColumnName = "InstrumentId"))
	private List<Instrument> instruments;
}
