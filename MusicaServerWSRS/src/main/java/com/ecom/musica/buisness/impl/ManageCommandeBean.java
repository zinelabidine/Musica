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

import com.ecom.musica.buisness.ManageCommandeBeanRemote;
import com.ecom.musica.entities.Commande;
import com.ecom.musica.entities.CommandeInstrument;
import com.ecom.musica.entities.Panier;
import com.ecom.musica.entities.PanierInstrument;
import com.ecom.musica.entities.Utilisateur;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ManageCommandeBean implements ManageCommandeBeanRemote {
    @PersistenceContext(unitName = "EntityManagerPU")
    private EntityManager entityManager;

    @Override
    public Commande getCommandeEnCours(int commandeId) throws Exception {
        Commande commande = entityManager.find(Commande.class, commandeId);
        if (commande == null)
            throw new Exception("La commande n'existe pas");
        commande.getLignesCommande().size();
        for (CommandeInstrument ligneCommande : commande.getLignesCommande()) {
            ligneCommande.getInstrument().getPromotions().size();
            ligneCommande.getInstrument().getMusiciens().size();
        }
        return commande;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void abondonnerCommande(int commandeId) throws Exception {
        // TODO Auto-generated method stub
        Commande commande = entityManager.find(Commande.class, commandeId);
        if (commande == null)
            throw new Exception("La commande n'existe pas");
        commande.getLignesCommande().size();
        for (CommandeInstrument ligneCommande : commande.getLignesCommande()) {// verifier
                                                                               // le
                                                                               // stock
            entityManager.remove(ligneCommande);
        }
        entityManager.remove(commande);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void supprimerLigneCommande(int commandeInstumentId) throws Exception {
        // TODO Auto-generated method stub
        CommandeInstrument ligneCommande = entityManager.find(CommandeInstrument.class, commandeInstumentId);
        if (ligneCommande == null)
            throw new Exception("La commande n'existe pas");
        Commande commande = ligneCommande.getCommande();
        commande.setMontantHT(
                commande.getMontantHT() - (ligneCommande.getInstrument().getPrix() * ligneCommande.getQuantite()));
        commande.setMontantTTC(
                commande.getMontantTTC() - (ligneCommande.getInstrument().getPrix() * ligneCommande.getQuantite()));
        entityManager.merge(commande);
        entityManager.remove(ligneCommande);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void modifierLigneCommande(int commandeInstumentId, int quantite) throws Exception {
        // TODO Auto-generated method stub
        CommandeInstrument ligneCommande = entityManager.find(CommandeInstrument.class, commandeInstumentId);
        if (ligneCommande == null)
            throw new Exception("La commande n'existe pas");
        if (ligneCommande.getInstrument().getQuantite() < quantite)
            throw new Exception("La quantité en stock est insuffisante");
        Commande commande = ligneCommande.getCommande();
        commande.setMontantHT(
                commande.getMontantHT() - (ligneCommande.getInstrument().getPrix() * ligneCommande.getQuantite())
                        + (ligneCommande.getInstrument().getPrix() * quantite));
        commande.setMontantTTC(
                commande.getMontantTTC() - (ligneCommande.getInstrument().getPrix() * ligneCommande.getQuantite())
                        + (ligneCommande.getInstrument().getPrix() * quantite));
        ligneCommande.setQuantite(quantite);
        entityManager.merge(commande);
        entityManager.merge(ligneCommande);
    }

    private List<Commande> findCommandesByUtilisateur(Utilisateur utilisateur) {
        Query req = entityManager.createQuery("select c from Commande c  where c.utilisateur=:utilisateur ");
        req.setParameter("utilisateur", utilisateur);
        return req.getResultList();
    }

    @Override
    public List<Commande> getCommandesByUtilisateur(int utilisateurId) throws Exception {
        // TODO Auto-generated method stub
        Utilisateur utilisateur = entityManager.find(Utilisateur.class, utilisateurId);
        if (utilisateur == null)
            throw new Exception("Le client n'existe pas!");
        return findCommandesByUtilisateur(utilisateur);
    }

}
