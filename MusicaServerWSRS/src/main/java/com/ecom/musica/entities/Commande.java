package com.ecom.musica.entities;

import java.io.Serializable;
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
@Table(name = "Commande")
public class Commande implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CommandeId")
	private int commandeId;

	@ManyToOne
	@JoinColumn(name = "ClientPasseId")
	private Client clientPasseCommande;

	@ManyToOne
	@JoinColumn(name = "ClientPayeId")
	private Client clientPayeCommande;

	@Column(name = "MontantTTC")
	private float montantTTC;

	@Column(name = "MontantHT")
	private float montantHT;

	@ManyToMany
	@JoinTable(name = "CommandeInstrument", joinColumns = @JoinColumn(name = "CommandeId", referencedColumnName = "CommandeId"), inverseJoinColumns = @JoinColumn(name = "InstrumentId", referencedColumnName = "InstrumentId"))
	private List<Instrument> instruments;
}
