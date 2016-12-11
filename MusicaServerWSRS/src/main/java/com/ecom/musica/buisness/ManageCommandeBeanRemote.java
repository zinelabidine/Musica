package com.ecom.musica.buisness;

import java.util.List;

import com.ecom.musica.entities.Commande;

public interface ManageCommandeBeanRemote {

    public Commande getCommandeEnCours(int commandeId) throws Exception;

    public void abondonnerCommande(int commandeId) throws Exception;

    public void supprimerLigneCommande(int commandeInstumentId) throws Exception;

    public void modifierLigneCommande(int commandeInstumentId, int quantite) throws Exception;

    public List<Commande> getCommandesByUtilisateur(int utilisateurId) throws Exception;

}
