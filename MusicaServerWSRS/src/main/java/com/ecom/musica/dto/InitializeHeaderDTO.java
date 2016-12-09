package com.ecom.musica.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.ecom.musica.entities.Categorie;
import com.ecom.musica.entities.Marque;

@XmlRootElement
public class InitializeHeaderDTO {
	private List<Categorie> Categories;
	private List<Marque> Marques;

	public List<Categorie> getCategories() {
		return Categories;
	}

	public void setCategories(List<Categorie> categories) {
		Categories = categories;
	}

	public List<Marque> getMarques() {
		return Marques;
	}

	public void setMarques(List<Marque> marques) {
		Marques = marques;
	}
}
