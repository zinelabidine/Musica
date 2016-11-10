package com.ecom.musica.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//TODO:: @OneToMany and @ManyToMany
@Entity
@Table(name = "Administrateur")
public class Administrateur {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AdministrateurId")
	private int administrateurId;

	@ManyToOne
	@JoinColumn(name = "ProfilId")
	private Profil profil;

	@Column(name = "Nom")
	private String nom;

	@Column(name = "Prenom")
	private String prenom;

	@Column(name = "Email")
	private String email;

	@Column(name = "Telephone")
	private String telephone;

	@Column(name = "Login")
	private String login;

	@Column(name = "MotDePasse")
	private String motDePasse;
}
