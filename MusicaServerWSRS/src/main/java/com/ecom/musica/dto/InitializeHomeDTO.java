package com.ecom.musica.dto;

import java.util.List;

import com.ecom.musica.entities.Categorie;
import com.ecom.musica.entities.Instrument;

public class InitializeHomeDTO {
	public List<Instrument> InstrumentsBestSales;
	public List<Instrument> InstrumentsPromotions;
	public List<Categorie> Categories;
}
