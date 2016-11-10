package com.ecom.musica.buisness;

import javax.ejb.Remote;

import com.ecom.musica.entities.Instrument;

@Remote
public interface ManageInstrumentBeanRemote {
	public boolean addInstrument(Instrument instrument);
}
