package com.ecom.musica.buisness.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ecom.musica.buisness.ManagePanierBeanRemote;
import com.ecom.musica.entities.Client;
import com.ecom.musica.entities.Instrument;
import com.ecom.musica.entities.Panier;
import com.ecom.musica.entities.PanierInstrument;

@Stateless
public class ManagePanierBean implements ManagePanierBeanRemote {

    @PersistenceContext(unitName = "EntityManagerPU")
    private EntityManager entityManager;
    
    @Override
    public void addToPanier(int clientId, int instrumentId, int quantite) throws Exception {

        List<PanierInstrument> lignesPanier = new ArrayList<PanierInstrument>();
        Client client = entityManager.find(Client.class, clientId);
        Instrument instrument = entityManager.find(Instrument.class, instrumentId);
        if (client == null || instrument == null)
            return;
        PanierInstrument lignePanier = new PanierInstrument();
        lignePanier.setInstrument(instrument);
        lignePanier.setQuantite(quantite);
        List<Panier> listPanier = findPanierByClient(client);
        Panier panier = null;
        if (listPanier.size() > 0) {
            panier = listPanier.get(0);
            lignePanier.setPanier(panier);
        }
        if (panier == null) {// le panier n'existe pas et on le crée
            panier = new Panier();
            panier.setClient(client);
            entityManager.persist(panier);// on l'insert
            panier = findPanierByClient(client).get(0);// on recupere son id
            lignePanier.setPanier(panier);// on contruit la ligne du panier
        } else {
            Query req = entityManager.createQuery(
                    "select p from PanierInstrument p  where p.instrument=:instrument and p.panier=:panier");
            req.setParameter("instrument", instrument);
            req.setParameter("panier", panier);
            lignesPanier = req.getResultList();
            for (PanierInstrument ligne : lignesPanier) {// si l'instrument
                                                         // existe deja dans
                                                         // une ligne alors
                                                         // exception
                if (ligne.getInstrument() == lignePanier.getInstrument())
                    throw new Exception("instrument deja existant");
            }
        }
        if (instrument.getQuantite() >= quantite) // on verifie la quantité
            entityManager.persist(lignePanier);
        else
            throw new Exception("quantité insuffisante");

    }

    private List<Panier> findPanierByClient(Client client) {
        Query req = entityManager.createQuery("select p from Panier p  where p.client=:client");
        req.setParameter("client", client);
        return req.getResultList();
    }

}
