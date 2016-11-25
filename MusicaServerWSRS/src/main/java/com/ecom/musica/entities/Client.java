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
	private String email;
	
	@Column(name = "Telephone")
	private String telephone;
	
	@Column(name = "Login")
	private String login;
	
	@Column(name = "MotDePasse")
	private String motDePasse;

	@Column(name = "Adresse")
    private String addresse;

	@Column(name = "Ville")
    private String ville;

    @OneToMany(mappedBy="clientPayeCommande")
    private List<Commande> commandesPayes;

    @OneToMany(mappedBy="clientPasseCommande")
    private List<Commande> commandesPasses;

    @OneToMany(mappedBy="client")
    private List<Panier> paniers;

    public Client (String login, String mdp, String email) {
        super();
        this.login = login;
        this.motDePasse = mdp;
        this.email = email;
    }

	public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    @Column(name = "Pays")
    private String pays;

	@Column(name = "CP")
    private String cp;
	
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

    public List<Commande> getCommandesPayes() {
        return commandesPayes;
    }

    public void setCommandesPayes(List<Commande> commandesPayes) {
        this.commandesPayes = commandesPayes;
    }

    public List<Commande> getCommandesPasses() {
        return commandesPasses;
    }

    public void setCommandesPasses(List<Commande> commandesPasses) {
        this.commandesPasses = commandesPasses;
    }

    public List<Panier> getPaniers() {
        return paniers;
    }

    public void setPaniers(List<Panier> paniers) {
        this.paniers = paniers;
    }
}
