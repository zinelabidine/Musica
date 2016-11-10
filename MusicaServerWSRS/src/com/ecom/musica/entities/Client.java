package com.ecom.musica.entities;

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
public class Client {
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
	
	@OneToMany(mappedBy="clientPayeCommande")
	private List<Commande> commandesPayes;
	
	@OneToMany(mappedBy="clientPasseCommande")
	private List<Commande> commandesPasses;
	
	@OneToMany(mappedBy="client")
	private List<Panier> paniers;
}
