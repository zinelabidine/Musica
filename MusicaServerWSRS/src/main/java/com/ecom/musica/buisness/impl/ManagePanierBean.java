package com.ecom.musica.buisness.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ecom.musica.buisness.ManagePanierBeanRemote;
import com.ecom.musica.entities.Commande;
import com.ecom.musica.entities.CommandeInstrument;
import com.ecom.musica.entities.Instrument;
import com.ecom.musica.entities.Panier;
import com.ecom.musica.entities.PanierInstrument;
import com.ecom.musica.entities.Utilisateur;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ManagePanierBean implements ManagePanierBeanRemote {

    @PersistenceContext(unitName = "EntityManagerPU")
    private EntityManager entityManager;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void addToPanier(int utilisateurId, int instrumentId, int quantite) throws Exception {
        List<PanierInstrument> lignesPanier = new ArrayList<PanierInstrument>();
        Utilisateur utilisateur = entityManager.find(Utilisateur.class, utilisateurId);
        Instrument instrument = entityManager.find(Instrument.class, instrumentId);
        if (utilisateur == null || instrument == null)
            return;
        PanierInstrument lignePanier = new PanierInstrument();
        lignePanier.setInstrument(instrument);
        lignePanier.setQuantite(quantite);
        Panier panier = findPanierByUtilisateur(utilisateur);
        if (panier != null) {
            lignePanier.setPanier(panier);
        }
        if (panier == null) {// le panier n'existe pas et on le crée
            panier = new Panier();
            panier.setUtilisateur(utilisateur);
            panier.setValide(false);
            entityManager.persist(panier);// on l'insert
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
        {
            entityManager.persist(lignePanier);
            panier.setMontantTTC(panier.getMontantTTC() + instrument.getPrix() * quantite);// update
                                                                                           // le
                                                                                           // mt
                                                                                           // du
                                                                                           // panier
            panier.setMontantHT(panier.getMontantHT() + instrument.getPrix() * quantite);
            entityManager.merge(panier);
        } else
            throw new Exception("quantité insuffisante");

    }

    private Panier findPanierByUtilisateur(Utilisateur utilisateur) {
        Query req = entityManager.createQuery("select p from Panier p  where p.utilisateur=:utilisateur");
        req.setParameter("utilisateur", utilisateur);
        List<Panier> listPanier = req.getResultList();
        if (listPanier.size() > 0)
            return listPanier.get(0);
        else
            return null;
    }

    @Override
    public void validerPanier(int utilisateurId, int panierId) throws Exception {
        // TODO Auto-generated method stub
        Panier panier = entityManager.find(Panier.class, panierId);
        if (panier == null)
            throw new Exception("panier Innexistant");
        if (panier.getUtilisateur().getUtilisateurId() != utilisateurId)
            throw new Exception("Ce panier n'appartient pas a ce client");
        if (panier.getLignesPanier().size() < 1)
            throw new Exception("Ce panier est vide");
        panier.setValide(true);
        entityManager.merge(panier);
        // ou bien ajouter le panier dans la commande ,ou bien faire la
        // persistance du tout
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void supprimerLignePanier(int panierInstrumentId) throws Exception {
        // TODO Auto-generated method stub
        PanierInstrument lignePanier = entityManager.find(PanierInstrument.class, panierInstrumentId);
        if (lignePanier == null)
            throw new Exception("La ligne n'existe pas");
        Panier panier = lignePanier.getPanier();
        panier.setMontantHT(
                panier.getMontantHT() - (lignePanier.getInstrument().getPrix() * lignePanier.getQuantite()));
        panier.setMontantTTC(
                panier.getMontantTTC() - (lignePanier.getInstrument().getPrix() * lignePanier.getQuantite()));
        entityManager.merge(panier);
        entityManager.remove(lignePanier);
        //TODO updater le montant du panier
    }

    @Override
    public Panier getPanier(int utilisateurId) throws Exception {
        // TODO Auto-generated method stub
        Utilisateur utilisateur = entityManager.find(Utilisateur.class, utilisateurId);
        Panier panier = findPanierByUtilisateur(utilisateur);
        if (panier == null)
            throw new Exception("Ce client n'a pas de panier");
        panier.getLignesPanier().size();
        for (PanierInstrument lignePanier : panier.getLignesPanier()) {
            lignePanier.getInstrument().getPromotions().size();
            lignePanier.getInstrument().getMusiciens().size();
        }
        return panier;      
    }

    @Override
    public int countInstrumentInPanier(int clientId) throws Exception {
        // TODO Auto-generated method stub
        Utilisateur client = entityManager.find(Utilisateur.class, clientId);
        Panier panier = findPanierByUtilisateur(client);
        if (panier == null)
            throw new Exception("Ce client n'a pas de panier");
        return panier.getLignesPanier().size();      
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void modifierLignePanier(int panierInstrumentId, int quantite) throws Exception {
        // TODO Auto-generated method stub
        PanierInstrument lignePanier = entityManager.find(PanierInstrument.class, panierInstrumentId);
        if (lignePanier == null)
            throw new Exception("Cette ligne n'existe pas");
        if (lignePanier.getInstrument().getQuantite()<quantite)
            throw new Exception("quantité insuffisante");
        Panier panier = lignePanier.getPanier();
        panier.setMontantHT(
                panier.getMontantHT() - (lignePanier.getInstrument().getPrix() * lignePanier.getQuantite())
                        + (lignePanier.getInstrument().getPrix() * quantite));
        panier.setMontantTTC(
                panier.getMontantTTC() - (lignePanier.getInstrument().getPrix() * lignePanier.getQuantite())
                        + (lignePanier.getInstrument().getPrix() * quantite));
        lignePanier.setQuantite(quantite);
        entityManager.merge(lignePanier);
        entityManager.merge(panier);
    }
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public int payerPanier(int panierId, int utilisateurId) throws Exception {
        // TODO Auto-generated method stub
        Panier panier = entityManager.find(Panier.class, panierId);
        if (panier == null)
            throw new Exception("panier Innexistant");
        if (panier.getUtilisateur().getUtilisateurId() != utilisateurId)
            throw new Exception("Ce panier n'appartient pas a ce client");
        if (panier.getLignesPanier().size() < 1)
            throw new Exception("Ce panier est vide");
        Commande commande = new Commande();
        List<CommandeInstrument> lignesCommande = new ArrayList<CommandeInstrument>();
        transformPanierToCommande(panier, commande, lignesCommande);
        entityManager.persist(commande);
        for (CommandeInstrument ligneCommande : lignesCommande) {
            entityManager.persist(ligneCommande);
        }
        entityManager.remove(panier);
        return commande.getCommandeId();
    }
    private void transformPanierToCommande(Panier panier, Commande commande, List<CommandeInstrument> lignesCommande) {
        commande.setUtilisateur(panier.getUtilisateur());
        commande.setMontantHT(panier.getMontantHT());
        commande.setMontantTTC(panier.getMontantTTC());
        List<PanierInstrument> lignesPanier = panier.getLignesPanier();
        for (PanierInstrument lignePanier : lignesPanier) {
            lignesCommande
                    .add(new CommandeInstrument(commande, lignePanier.getInstrument(), lignePanier.getQuantite()));
            entityManager.remove(lignePanier);
        }
        commande.setLignesCommande(lignesCommande);
    }

    @Override
    public void annulerValidationPanier(int utilisateurId, int panierId) throws Exception {
        // TODO Auto-generated method stub
        Panier panier = entityManager.find(Panier.class, panierId);
        if (panier == null)
            throw new Exception("panier Innexistant");
        if (panier.getUtilisateur().getUtilisateurId() != utilisateurId)
            throw new Exception("Ce panier n'appartient pas a ce client");
        if (panier.getLignesPanier().size() < 1)
            throw new Exception("Ce panier est vide");
        panier.setValide(false);
        entityManager.merge(panier);
    }

}
