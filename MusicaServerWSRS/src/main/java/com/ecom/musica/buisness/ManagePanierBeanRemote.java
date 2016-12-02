package com.ecom.musica.buisness;

import javax.ejb.Remote;

import com.ecom.musica.entities.Commande;
import com.ecom.musica.entities.Panier;

@Remote
public interface ManagePanierBeanRemote {

    public void addToPanier(int utlisateurId, int instrumentId, int quantite) throws Exception;

    public void validerPanier(int utlisateurId, int panierId) throws Exception;

    public void supprimerLignePanier(int panierInstrumentId) throws Exception;

    public Panier getPanier(int utlisateurId) throws Exception;

    public void modifierLignePanier(int panierInstrumentId, int quantite) throws Exception;

    public int payerPanier(int panierId, int utlisateurId) throws Exception;

    public void annulerValidationPanier(int utlisateurId, int panierId) throws Exception;

    public int countInstrumentInPanier(int clientId) throws Exception;

}
