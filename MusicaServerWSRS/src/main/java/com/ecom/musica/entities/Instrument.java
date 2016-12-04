package com.ecom.musica.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Instrument")
public class Instrument implements Serializable {

    public Instrument(Marque marque2, Categorie categorie2, String reference2, int quantite2, float prix2,
            String description2, String image2) {
        this.marque = marque2;
        this.categorie = categorie2;
        this.reference = reference2;
        this.quantite = quantite2;
        this.prix = prix2;
        this.description = description2;
        this.image = image2;
    }

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

    @OneToOne(mappedBy = "instrument", fetch = FetchType.LAZY)
	private ImageInstrument imageInstrument;

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

    public List<CommandeInstrument> getLignesCommande() {
        return lignesCommande;
    }

    public void setLignesCommande(List<CommandeInstrument> lignesCommande) {
        this.lignesCommande = lignesCommande;
    }

    public List<PanierInstrument> getLignesPanier() {
        return lignesPanier;
    }

    public void setLignesPanier(List<PanierInstrument> lignesPanier) {
        this.lignesPanier = lignesPanier;
    }

    public ImageInstrument getImageInstrument() {
		return imageInstrument;
	}

	public void setImageInstrument(ImageInstrument imageInstrument) {
		this.imageInstrument = imageInstrument;
	}
}
