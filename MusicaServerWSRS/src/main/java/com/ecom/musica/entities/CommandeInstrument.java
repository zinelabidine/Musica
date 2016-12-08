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
@Table(name = "commande_instrument")
public class CommandeInstrument implements Serializable{
    public CommandeInstrument(Commande commande,Instrument instrument,int quantite)
    {
        this.commande = commande;
        this.instrument = instrument;
        this.Quantite = quantite;
        
    }
    public CommandeInstrument()
    {
        
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CommandeInstrumentId")
    private int commandeInstrumentId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "CommandeId")
    private Commande commande;
    
    @ManyToOne
    @JoinColumn(name = "InstrumentId")
    private Instrument instrument;
    
    @Column(name = "Quantite")
    private int Quantite;
    
    public int getCommandeInstrumentId() {
        return commandeInstrumentId;
    }

    public void setCommandeInstrumentId(int commandeInstrumentId) {
        this.commandeInstrumentId = commandeInstrumentId;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
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
