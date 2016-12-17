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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "musicien")
public class Musicien implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MusicienId")
	private int  musicienId ;
	
	@Column(name = "NomDeScene")
	private String nomDeScene ; 
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "instrument_musicien", joinColumns = @JoinColumn(name = "MusicienId", referencedColumnName = "MusicienId"), inverseJoinColumns = @JoinColumn(name = "InstrumentId", referencedColumnName = "InstrumentId"))
	private List<Instrument> instruments;

	public int getMusicienId() {
		return musicienId;
	}

	public void setMusicienId(int musicienId) {
		this.musicienId = musicienId;
	}

	public String getNomDeScene() {
		return nomDeScene;
	}

	public void setNomDeScene(String nomDeScene) {
		this.nomDeScene = nomDeScene;
	}

	public List<Instrument> getInstruments() {
		return instruments;
	}

	public void setInstruments(List<Instrument> instruments) {
		this.instruments = instruments;
	}
	
	
}
