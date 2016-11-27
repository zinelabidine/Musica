package com.ecom.musica.buisness.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ecom.musica.buisness.ManageInstrumentBeanRemote;
import com.ecom.musica.entities.Instrument;


/**
 * Session Bean implementation class ManageInstrumentBean
 */
@Stateless
public class ManageInstrumentBean implements ManageInstrumentBeanRemote {

    @PersistenceContext(unitName = "EntityManagerPU")
    private EntityManager entityManager;

    /**
     * Default constructor.
     */
    public ManageInstrumentBean() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean addInstrument(Instrument instrument) {
        entityManager.persist(instrument);
        return false;
    }

    @Override
    public List<Instrument> getAllInstruments() {
    	Query req = entityManager.createQuery("select i from Instrument i");
        List<Instrument> instruments = req.getResultList();
        for (Instrument instrument : instruments) {
            instrument.getPromotions().size();
        }
        return instruments;
    }


    @Override
    public List<Instrument> getInstrumentsPromotion() {
        Query req = entityManager.createQuery("select i  from Instrument i inner join i.promotions");
        List<Instrument> instruments = req.getResultList();
        for (Instrument instrument : instruments) {
            instrument.getPromotions().size();
        }
        return instruments;
    }

    // Convertor
    public static <T> List<T> castResultToList(Class<? extends T> c, int position, List<Object[]> dbResult) {
        List<T> castedList = new ArrayList<T>(dbResult.size());
        for (Object[] o : dbResult) {
            castedList.add((T) o[position]);
        }
        return castedList;
    }

    @Override
    public List<Instrument> getInstrumentsBestSales() {
	Query req = entityManager.createQuery(
		"select i,count(i) as NbrVente from Instrument i inner join i.lignesCommande group by i.instrumentId order by NbrVente desc");
	List<Instrument> instruments = castResultToList(Instrument.class, 0, req.getResultList());
    for (Instrument instrument : instruments) {
        instrument.getPromotions().size();
    }
	return instruments;
    }

    public Instrument getInstrumentWithId(int instrument_id) {
        Instrument i =  entityManager.find(Instrument.class, instrument_id);      
        i.getPromotions().size();
        return i;
        
    }

    @Override
    public List<Instrument> getInstrumentsWithKey(String instrument_key) {
    	Query req = entityManager.createQuery(
                "select i"
                        + " from Instrument i, Marque m, Categorie c"
                        + " where (i.reference like '%"+instrument_key+"%'"
                        + " or m.libelle like '%"+instrument_key+"%'"
                        + " or c.libelle like '%"+instrument_key+"%')"
                        + " and i.marque = m.marqueId"
                        + " and i.categorie = c.categorieId");
        List<Instrument> instruments = req.getResultList();
        for (Instrument instrument : instruments) {
            instrument.getPromotions().size();
        }
        return instruments;
    }

    @Override
    public List<Instrument> findInstruments(String marque, String categorie, String instrument_ref) {    	
    	Query req = null;
    	if(marque.toString().equals("")) {
    		System.out.println("marque null");
    		if(instrument_ref.toString().equals("")){
    			// find with categorie
    			req = entityManager.createQuery(
    				"select i "
    						+ " from Instrument i ,Categorie c"
    						+ " Where c.libelle like '%"+categorie+"%'"
    						+ " and i.categorie = c.categorieId");
    		}
    		else{
    			// find with categorie et intrument_ref
    			req = entityManager.createQuery(
        				"select i "
        						+ " from Instrument i ,Categorie c"
        						+ " Where (i.reference like '%"+instrument_ref+"%'"
        					    + " and c.libelle like '%"+categorie+"%')"
        						+ " and i.categorie = c.categorieId");
    		}
    	}
    	else {
    		if(categorie.toString().equals("")){
    			if(instrument_ref.toString().equals("")){
	          		// find with marque
    				req = entityManager.createQuery(
    	    				"select i "
    	    						+ " from Instrument i ,Marque m"
    	    						+ " Where m.libelle like '%"+marque+"%'"
    	    						+ " and i.marque = m.marqueId");
    			}
    			else{
    				// find with marque et ref
    				req = entityManager.createQuery(
            				"select i "
            						+ " from Instrument i ,Marque m"
            						+ " Where (i.reference like '%"+instrument_ref+"%'"
            					    + " and m.libelle like '%"+marque+"%')"
            						+ " and i.marque = m.marqueId");
    			} 		
    		}
    		else {
    			if(instrument_ref.toString().equals("")){
    				// find with marque et categorie
    				req = entityManager.createQuery(
            				"select i "
            						+ " from Instrument i ,Marque m, Categorie c"
            						+ " Where (c.libelle like '%"+categorie+"%'"
            					    + " and m.libelle like '%"+marque+"%')"
            						+ " and i.marque = m.marqueId"
            						+ " and i.categorie = c.categorieId");
    			}
    			else{
    				// find with 3 criteres
    				req = entityManager.createQuery(
    				        "select i"
    				                + " from Instrument i, Marque m, Categorie c"
    				                + " where (i.reference like '%"+instrument_ref+"%'"
    				                + " and m.libelle like '%"+marque+"%'"
    				                + " and c.libelle like '%"+categorie+"%')"
    				                + " and i.marque = m.marqueId"
    				                + " and i.categorie = c.categorieId");  
    			}
    		}
	  	}
			  	
		List<Instrument> instruments = req.getResultList();
		for (Instrument instrument : instruments) {
			instrument.getPromotions().size();
		}
		return instruments;
    }
}
