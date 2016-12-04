package com.ecom.musica.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UtilisateurPersonalInformationDTO {
	@XmlElement
	private int utilisateurid;
	@XmlElement
	private String firstname;
	@XmlElement
	private String lastname;
	@XmlElement
	private String address;
	@XmlElement
	private String city;
	@XmlElement
	private String country;
	@XmlElement
	private String zip;
	@XmlElement
	private String tel;
	@XmlElement
	private String email;

	public int getUtilisateurid() {
		return utilisateurid;
	}

	public void setUtilisateurid(int utilisateurid) {
		this.utilisateurid = utilisateurid;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
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

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
