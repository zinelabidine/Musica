package com.ecom.musica.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "panier_instrument")
public class PanierInstrument implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PanierInstrumentId")
    private int panierInstrumentId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "PanierId")
    private Panier panier;

    @ManyToOne
    @JoinColumn(name = "InstrumentId")
    private Instrument instrument;

    @Column(name = "Quantite")
    private int Quantite;

    // getters and setters

    public int getPanierInstrumentId() {
        return panierInstrumentId;
    }

    public void setPanierInstrumentId(int panierInstrumentId) {
        this.panierInstrumentId = panierInstrumentId;
    }

    public Panier getPanier() {
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    public int getQuantite() {
        return Quantite;
    }

    public void setQuantite(int quantite) {
        Quantite = quantite;
    }
}
