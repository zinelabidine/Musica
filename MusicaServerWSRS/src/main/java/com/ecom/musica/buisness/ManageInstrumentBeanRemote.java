package com.ecom.musica.buisness;

import java.util.List;

import javax.ejb.Remote;
import javax.persistence.Query;
import javax.xml.bind.annotation.XmlElement;

import com.ecom.musica.entities.Instrument;

@Remote
public interface ManageInstrumentBeanRemote {
    public void addInstrument(int marqueId, int categorieId, String reference, int quantite, float prix,
            String description, String image)throws Exception;

    public List<Instrument> getAllInstruments();

    public List<Instrument> getInstrumentsBestSales();

    public List<Instrument> getInstrumentsPromotion();

    public Instrument getInstrumentWithId(int instrument_id);

    public List<Instrument> getInstrumentsWithKey(String instrument_key);

    public List<Instrument> findInstruments(String marque, String categorie, String instrument_ref);
}
