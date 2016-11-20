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

@Entity
@Table(name = "Panier")
public class Panier implements Serializable {
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
	
    @OneToMany(mappedBy = "panier")
    private List<PanierInstrument> lignesPanier;
    
	//getter and setters 
	
	public int getPanierId() {
        return panierId;
    }

    public void setPanierId(int panierId) {
        this.panierId = panierId;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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


}
