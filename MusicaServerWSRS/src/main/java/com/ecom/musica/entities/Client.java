package com.ecom.musica.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Client")
public class Client implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ClientId")
	private int clientId ;
	
	@Column(name = "NumeroCarteBancaire")
	private String numeroCarteBancaire ;
	
	@Column(name = "Nom")
	private String nom ;
	
	@Column(name = "Prenom")
	private String prenom ;
	
	@Column(name = "Email")
	private String email ;
	
	@Column(name = "Telephone")
	private String telephone ;
	
	@Column(name = "Login")
	private String login ;
	
	@Column(name = "MotDePasse")
	private String motDePasse ;
	
    @OneToMany(mappedBy="client")
    private List<Commande> commandes;
    
    
    @OneToMany(mappedBy="client")
    private List<Panier> paniers;
	
	public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getNumeroCarteBancaire() {
        return numeroCarteBancaire;
    }

    public void setNumeroCarteBancaire(String numeroCarteBancaire) {
        this.numeroCarteBancaire = numeroCarteBancaire;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }


    public List<Commande> getCommandesPasses() {
        return commandes;
    }

    public void setCommandesPasses(List<Commande> commandes) {
        this.commandes = commandes;
    }

    public List<Panier> getPaniers() {
        return paniers;
    }

    public void setPaniers(List<Panier> paniers) {
        this.paniers = paniers;
    }


}
