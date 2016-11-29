package com.ecom.musica.buisness;

import javax.ejb.Remote;

import com.ecom.musica.entities.Commande;
import com.ecom.musica.entities.Panier;

@Remote
public interface ManagePanierBeanRemote {

    public void addToPanier(int clientId, int instrumentId, int quantite) throws Exception;

    public void validerPanier(int clientId, int panierId) throws Exception;

    public void supprimerLignePanier(int panierInstrumentId) throws Exception;

    public Panier getPanier(int clientId) throws Exception;

    public void modifierLignePanier(int panierInstrumentId, int quantite) throws Exception;

    public int payerPanier(int panierId, int clientId) throws Exception;

    public void annulerValidationPanier(int clientId, int panierId) throws Exception;

}
