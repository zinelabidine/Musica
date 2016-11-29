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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Commande")
public class Commande implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CommandeId")
    private int commandeId;

    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ClientId")
    private Client client;

    @Column(name = "MontantTTC")
    private float montantTTC;

    @Column(name = "MontantHT")
    private float montantHT;

    @OneToMany(mappedBy = "commande")
    private List<CommandeInstrument> lignesCommande;
    
    //getters setters

    public int getCommandeId() {
        return commandeId;
    }

    public void setCommandeId(int commandeId) {
        this.commandeId = commandeId;
    }


    public Client getClient() {
        return client;
    }

    public void setClient(Client clientPayeCommande) {
        this.client = clientPayeCommande;
    }

    public float getMontantTTC() {
        return montantTTC;
    }

    public void setMontantTTC(float montantTTC) {
        this.montantTTC = montantTTC;
    }

    public float getMontantHT() {
        return montantHT;
    }

    public void setMontantHT(float montantHT) {
        this.montantHT = montantHT;
    }

    public List<CommandeInstrument> getLignesCommande() {
        return lignesCommande;
    }

    public void setLignesCommande(List<CommandeInstrument> lignesCommande) {
        this.lignesCommande = lignesCommande;
    }


}
