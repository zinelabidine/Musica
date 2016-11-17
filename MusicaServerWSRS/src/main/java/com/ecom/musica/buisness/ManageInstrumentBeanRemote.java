package com.ecom.musica.buisness;

import java.util.List;

import javax.ejb.Remote;
import javax.persistence.Query;

import com.ecom.musica.entities.Instrument;

@Remote
public interface ManageInstrumentBeanRemote {
	public boolean addInstrument(Instrument instrument);
	public List<Instrument> getAllInstruments() ;
	public List<Instrument> getInstrumentsBestSales();
	public List<Instrument> getInstrumentsPromotion();
	public Instrument getInstrumentWithId(int instrument_id);
}
