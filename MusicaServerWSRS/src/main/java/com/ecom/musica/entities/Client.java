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

	@Column(name = "Lastname")
	private String lastname ;

	@Column(name = "Firstname")
	private String firstname;

	@Column(name = "Email")
	private String email;

	@Column(name = "Tel")
	private String tel;
	
	@Column(name = "Login")
	private String login;
	
	@Column(name = "MDP")
	private String mdp;

	@Column(name = "Address")
    private String address;

	@Column(name = "City")
    private String city;

    @Column(name = "Country")
    private String country;

    @Column(name = "ZIP")
    private String zip;

    @OneToMany(mappedBy="clientPayeCommande")
    private List<Commande> commandesPayes;

    @OneToMany(mappedBy="clientPasseCommande")
    private List<Commande> commandesPasses;

    @OneToMany(mappedBy="client")
    private List<Panier> paniers;

    public Client () {
        super();
    }

    public Client (String login, String mdp, String email) {
        super();
        this.login = login;
        this.mdp = mdp;
        this.email = email;
    }

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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
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
