package com.ecom.musica.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.ecom.musica.entities.Instrument;

@XmlRootElement
public class InitializeHomeContentDTO {

	private List<Instrument> InstrumentsBestSales;
	private List<Instrument> InstrumentsPromotions;

	public List<Instrument> getInstrumentsBestSales() {
		return InstrumentsBestSales;
	}

	public void setInstrumentsBestSales(List<Instrument> instrumentsBestSales) {
		InstrumentsBestSales = instrumentsBestSales;
	}

	public List<Instrument> getInstrumentsPromotions() {
		return InstrumentsPromotions;
	}

	public void setInstrumentsPromotions(List<Instrument> instrumentsPromotions) {
		InstrumentsPromotions = instrumentsPromotions;
	}

}
