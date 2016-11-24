package com.ecom.musica.buisness.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ecom.musica.buisness.ManageCommandeBeanRemote;
import com.ecom.musica.entities.Client;
import com.ecom.musica.entities.Commande;
import com.ecom.musica.entities.CommandeInstrument;
import com.ecom.musica.entities.Panier;
import com.ecom.musica.entities.PanierInstrument;

@Stateless
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
        }
        return commande;
    }

    @Override
    public void payerCommande(int commandeId, int clientId) throws Exception {
        // TODO Auto-generated method stub
        Client client = entityManager.find(Client.class, clientId);
        if (client == null)
            throw new Exception("Le client n'existe pas");
        Commande commande = entityManager.find(Commande.class, commandeId);
        if (commande == null)
            throw new Exception("La commande n'existe pas");
        if (!commande.getClientPasseCommande().equals(client))
            throw new Exception("Cette commande n'appartient pas a ce client");
        commande.setClientPayeCommande(client);
        commande.getLignesCommande().size();
        for (CommandeInstrument ligneCommande : commande.getLignesCommande()) {// verifier
                                                                               // le
                                                                               // stock
            if (ligneCommande.getInstrument().getQuantite() < ligneCommande.getQuantite())
                throw new Exception("Le stock n'est plus suffisant pour l'instrument "
                        + ligneCommande.getInstrument().getReference());
        }
        entityManager.merge(commande);
    }


    @Override
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

    private List<Commande> findCommandesByClient(Client client) {
        Query req = entityManager.createQuery(
                "select c from Commande c  where c.clientPasseCommande=:client and c.clientPayeCommande is null");
        req.setParameter("client", client);
        return req.getResultList();
    }

}
