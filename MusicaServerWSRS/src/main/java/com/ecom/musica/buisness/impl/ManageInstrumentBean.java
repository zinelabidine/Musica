package com.ecom.musica.buisness.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.ecom.musica.buisness.ManageInstrumentBeanRemote;
import com.ecom.musica.entities.Categorie;
import com.ecom.musica.entities.Commande;
import com.ecom.musica.entities.ImageInstrument;
import com.ecom.musica.entities.Instrument;
import com.ecom.musica.entities.Marque;

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
    public void addInstrument(int marqueId, int categorieId, String reference, int quantite, float prix,
            String description, String image) throws Exception {
        Marque marque = entityManager.find(Marque.class, marqueId);
        if (marque == null)
            throw new Exception("La marque n'existe pas");
        Categorie categorie = entityManager.find(Categorie.class, categorieId);
        if (categorie == null)
            throw new Exception("La categorie n'existe pas");
        Instrument instrument = new Instrument(marque, categorie, reference, quantite, prix, description);
        ImageInstrument imageInstrument = new ImageInstrument(image,instrument);
        try {
            entityManager.persist(instrument);
            entityManager.persist(imageInstrument);
        } catch (Exception e) {
            throw new Exception("l'instrument n'a pas pu etre inseré");
        }
    }

    @Override
    public List<Instrument> getAllInstruments() {
        Query req = entityManager.createQuery("select i from Instrument i Left JOIN FETCH i.musiciens mu");
        List<Instrument> instruments = req.getResultList();
        for (Instrument instrument : instruments) {
            instrument.getPromotions().size();
        }
        return instruments;
    }

    @Override
    public List<Instrument> getInstrumentsPromotion() {
        Query req = entityManager.createQuery("select i  from Instrument i inner join i.promotions Left JOIN FETCH i.musiciens mu");
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
                "select i,count(i) as NbrVente from Instrument  i Left JOIN FETCH i.musiciens mu inner join i.lignesCommande group by i.instrumentId order by NbrVente desc");
        List<Instrument> instruments = castResultToList(Instrument.class, 0, req.getResultList());
        for (Instrument instrument : instruments) {
            instrument.getPromotions().size();
        }
        return instruments;
    }

    public Instrument getInstrumentWithId(int instrumentId) {
    	
    	/*
    	 * We can not do two join fetch because Hibernate 4 is too stupid to do that.
    	 * SELECT i FROM Instrument i Left JOIN FETCH i.promotions p Left JOIN FETCH i.musiciens m WHERE i.instrumentId = :instrumentId
    	 */
    	Query q = this.entityManager.createQuery(
    			"SELECT i FROM Instrument i Left JOIN FETCH i.promotions p WHERE i.instrumentId = :instrumentId");
    	q.setParameter("instrumentId", instrumentId);
    	Instrument i = (Instrument) q.getSingleResult();
    	
    	i.getMusiciens().size();
        return i;

    }

    @Override
    public List<Instrument> getInstrumentsWithKey(String instrumentKey) {
    	TypedQuery<Instrument> req = entityManager.createQuery("select i from Instrument i Left JOIN FETCH i.musiciens mu, Marque m, Categorie c "
				+ "where (i.reference like :instrumentKey or m.libelle like :instrumentKey "
				+ "or c.libelle like :instrumentKey) and i.marque = m.marqueId and i.categorie = c.categorieId", Instrument.class);

		req.setParameter("instrumentKey", "%" + instrumentKey + "%");
        List<Instrument> instruments = req.getResultList();
        for (Instrument instrument : instruments) {
            instrument.getPromotions().size();
        }
        return instruments;
    }

	@Override
	public List<Instrument> findInstruments(String marque, String categorie, String instrumentRef) {
		/*TypedQuery<Instrument> req = null;
		if (marque.toString().equals("")) {
			if (instrument_ref.toString().equals("")) {
				// find with categorie
				req = entityManager.createQuery(
						"select i from Instrument i Left JOIN FETCH i.musiciens mu,Categorie c"
								+ " Where c.libelle like '%" + categorie + "%'" + " and i.categorie = c.categorieId",
						Instrument.class);
			} else {
				// find with categorie et intrument_ref
				req = entityManager.createQuery("select i from Instrument i Left JOIN FETCH i.musiciens mu,Categorie c"
						+ " Where (i.reference like '%" + instrument_ref + "%'" + " and c.libelle like '%" + categorie
						+ "%')" + " and i.categorie = c.categorieId", Instrument.class);
			}
		} else {
			if (categorie.toString().equals("")) {
				if (instrument_ref.toString().equals("")) {
					// find with marque
					req = entityManager.createQuery(
							"select i from Instrument i Left JOIN FETCH i.musiciens mu,Marque m"
									+ " Where m.libelle like '%" + marque + "%'" + " and i.marque = m.marqueId",
							Instrument.class);
				} else {
					// find with marque et ref
					req = entityManager.createQuery("select i from Instrument i Left JOIN FETCH i.musiciens mu,Marque m"
							+ " Where (i.reference like '%" + instrument_ref + "%'" + " and m.libelle like '%" + marque
							+ "%')" + " and i.marque = m.marqueId", Instrument.class);
				}
			} else {
				if (instrument_ref.toString().equals("")) {
					// find with marque et categorie
					req = entityManager.createQuery(
							"select i from Instrument i Left JOIN FETCH i.musiciens mu,Marque m, Categorie c"
									+ " Where (c.libelle like '%" + categorie + "%'" + " and m.libelle like '%" + marque
									+ "%')" + " and i.marque = m.marqueId" + " and i.categorie = c.categorieId",
							Instrument.class);
				} else {
					// find with 3 criteres
					req = entityManager.createQuery(
							"select i from Instrument i Left JOIN FETCH i.musiciens mu, Marque m, Categorie c"
									+ " where (i.reference like '%" + instrument_ref + "%'" + " and m.libelle like '%"
									+ marque + "%'" + " and c.libelle like '%" + categorie + "%')"
									+ " and i.marque = m.marqueId" + " and i.categorie = c.categorieId",
							Instrument.class);
				}
			}
		}
		*/
		
		TypedQuery<Instrument> req = entityManager
				.createQuery("select i from Instrument i Left JOIN FETCH i.musiciens mu, Marque m,"
						+ " Categorie c where (LOWER(i.reference) like LOWER(:instrumentRef) "
						+ "and LOWER(m.libelle) like LOWER(:marque) and LOWER(c.libelle) like LOWER(:categorie)) "
						+ "and i.marque = m.marqueId and i.categorie = c.categorieId", Instrument.class);
		// Uses of parameters to decline SQL injection
		req.setParameter("instrumentRef", "%" + instrumentRef + "%");
		req.setParameter("marque", "%" + marque + "%");
		req.setParameter("categorie", "%" + categorie + "%");
		List<Instrument> instruments = req.getResultList();
		for (Instrument instrument : instruments) {
			instrument.getPromotions().size();
		}
		return instruments;
	}
}
