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
import javax.persistence.Table;

@Entity
@Table(name = "Musicien")
public class Musicien {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MusicienId")
	private int  musicienId ;
	
	@Column(name = "NomDeScene")
	private String nomDeScene ; 
	
	@ManyToMany
	@JoinTable(name = "InstrumentMusicien", joinColumns = @JoinColumn(name = "MusicienId", referencedColumnName = "MusicienId"), inverseJoinColumns = @JoinColumn(name = "InstrumentId", referencedColumnName = "InstrumentId"))
	private List<Instrument> instruments;
}
