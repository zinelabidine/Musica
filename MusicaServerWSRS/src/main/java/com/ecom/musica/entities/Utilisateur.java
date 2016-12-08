package com.ecom.musica.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "utilisateur")
public class Utilisateur implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UtilisateurId")
    private int utilisateurId ;

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

    @JsonIgnore
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

    @Column(name = "Cardname")
    private String cardname;
    
    @Column(name = "Cardnumber")
    private String cardnumber;

    @Column(name = "Cardmonth")
    private String cardmonth;
    
    @Column(name = "Cardyear")
    private String cardyear;

    @OneToMany(mappedBy="utilisateur")
    private List<Commande> commandes;

    @Transient
    @OneToMany(mappedBy="utilisateur")
    private List<Panier> paniers;
    
    @ManyToOne
    @JoinColumn(name = "ProfilId")
    private Profil profil;

    public Utilisateur () {
        super();
    }

    public Utilisateur (String login, String mdp, String email) {
        super();
        this.login = login;
        this.mdp = mdp;
        this.email = email;
    }

    public int getUtilisateurId() {
        return utilisateurId;
    }

    public void setClientId(int clientId) {
        this.utilisateurId = clientId;
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

    public String getCardname() {
        return cardname;
    }

    public void setCardname(String cardname) {
        this.cardname = cardname;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public String getCardmonth() {
        return cardmonth;
    }

    public void setCardmonth(String cardmonth) {
        this.cardmonth = cardmonth;
    }

    public String getCardyear() {
        return cardyear;
    }

    public void setCardyear(String cardyear) {
        this.cardyear = cardyear;
    }

    public List<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(List<Commande> commandes) {
        this.commandes = commandes;
    }

    public List<Panier> getPaniers() {
        return paniers;
    }

    public void setPaniers(List<Panier> paniers) {
        this.paniers = paniers;
    }
    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }
}
