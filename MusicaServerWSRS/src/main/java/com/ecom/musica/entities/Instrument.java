package com.ecom.musica.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Instrument")
public class Instrument implements Serializable {


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

    @JsonIgnore
    @OneToMany(mappedBy = "instrument")
    private List<CommandeInstrument> lignesCommande;
    
    @JsonIgnore
    @OneToMany(mappedBy = "instrument")
    private List<PanierInstrument> lignesPanier;

    @Transient
    @ManyToMany(mappedBy = "instruments")
    private List<Musicien> musiciens;

    
    @ManyToMany(mappedBy = "instruments")
    private List<Promotion> promotions;

    @Column(name = "Reference")
    private String reference;

    @Column(name = "Quantite")
    private int quantite;

    @Column(name = "Prix")
    private float prix;

    @Column(name = "Description")
    private String description;

    @Lob
    @Column(name = "Image")
    private String image;

    public int getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(int instrumentId) {
        this.instrumentId = instrumentId;
    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public List<Musicien> getMusiciens() {
        return musiciens;
    }

    public void setMusiciens(List<Musicien> musiciens) {
        this.musiciens = musiciens;
    }

    public List<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }
}
