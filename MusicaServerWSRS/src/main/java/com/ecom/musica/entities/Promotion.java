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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Promotion")
public class Promotion implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PromotionId")
	private int promotionId;
	
	public int getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(int promotionId) {
        this.promotionId = promotionId;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public float getTaux() {
        return taux;
    }

    public void setTaux(float taux) {
        this.taux = taux;
    }

    public List<Instrument> getInstruments() {
        return instruments;
    }

    public void setInstruments(List<Instrument> instruments) {
        this.instruments = instruments;
    }

    public List<Marque> getMarques() {
        return marques;
    }

    public void setMarques(List<Marque> marques) {
        this.marques = marques;
    }

    @Column(name = "Libelle")
	private String libelle;
	
	@Column(name = "dateDebut")
	private Date dateDebut;
	
	@Column(name = "DateFin")
	private Date dateFin;
	
	@Column(name = "Taux")
	private float taux;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "InstrumentPromotion", joinColumns = @JoinColumn(name = "PromotionId", referencedColumnName = "PromotionId"), inverseJoinColumns = @JoinColumn(name = "InstrumentId", referencedColumnName = "InstrumentId"))
	private List<Instrument> instruments;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "MarquePromotion", joinColumns = @JoinColumn(name = "PromotionId", referencedColumnName = "PromotionId"), inverseJoinColumns = @JoinColumn(name = "MarqueId", referencedColumnName = "MarqueId"))
	private List<Marque> marques;
}
