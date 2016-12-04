package com.ecom.musica.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.ecom.musica.entities.Categorie;

@XmlRootElement
public class InitializeHeaderDTO {
	private List<Categorie> Categories;

	public List<Categorie> getCategories() {
		return Categories;
	}

	public void setCategories(List<Categorie> categories) {
		Categories = categories;
	}

}
