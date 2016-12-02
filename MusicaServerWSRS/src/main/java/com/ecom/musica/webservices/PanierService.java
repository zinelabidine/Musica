package com.ecom.musica.webservices;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.ecom.musica.buisness.ManagePanierBeanRemote;
import com.ecom.musica.entities.Commande;
import com.ecom.musica.entities.Panier;

@Stateless
@LocalBean
@Path("/panier")
public class PanierService {
    @EJB
    private ManagePanierBeanRemote panier;

    @GET
    @Path("/add/{clientid}/{instrumentid}/{quantite}")
    @Produces("application/json")
    public void addToPanier(@PathParam("clientid") String clientParam,
            @PathParam("instrumentid") String instrumentParam, @PathParam("quantite") String quantiteParam)
            throws Exception {
        try {
            int clientId = Integer.parseInt(clientParam);
            int instrumentId = Integer.parseInt(instrumentParam);
            int quantite = Integer.parseInt(quantiteParam);
            panier.addToPanier(clientId, instrumentId, quantite);
        } catch (NumberFormatException e) {
            throw new Exception("Erreur dans les parametres");
        }
    }

    @GET
    @Path("/valider/{clientid}/{panierid}")
    @Produces("application/json")
    public void validerPanier(@PathParam("clientid") String clientParam, @PathParam("panierid") String panierParam)
            throws Exception {
        try {
            int clientId = Integer.parseInt(clientParam);
            int panierId = Integer.parseInt(panierParam);
            panier.validerPanier(clientId, panierId);
        } catch (NumberFormatException e) {
            throw new Exception("Erreur dans les parametres");
        }
    }

    @GET
    @Path("/supprimer/{panierInstumentId}")
    @Produces("application/json")
    public void supprimerLignePanier(@PathParam("panierInstumentId") String panierInstumentIdParam) throws Exception {
        try {
            int panierInstumentId = Integer.parseInt(panierInstumentIdParam);
            panier.supprimerLignePanier(panierInstumentId);
        } catch (NumberFormatException e) {
            throw new Exception("Erreur dans les parametres");
        }
    }

    @GET
    @Path("/getpanier/{clientId}")
    @Produces("application/json")
    public Panier getPanier(@PathParam("clientId") String clientIdParam) throws Exception {
        try {
            int clientId = Integer.parseInt(clientIdParam);
            return panier.getPanier(clientId);
        } catch (NumberFormatException e) {
            throw new Exception("Erreur dans les parametres");
        }
    }

    @GET
    @Path("/paniersize/{clientId}")
    @Produces("application/json")
    public int getPanierSize(@PathParam("clientId") String clientIdParam) throws Exception {
        try {
            int clientId = Integer.parseInt(clientIdParam);
            return panier.countInstrumentInPanier(clientId);
        } catch (NumberFormatException e) {
            throw new Exception("Erreur dans les parametres");
        }
    }

    @GET
    @Path("/modifierligne/{panierInstrumentId}/{quantite}")
    @Produces("application/json")
    public void modifierLignePanier(@PathParam("panierInstrumentId") String panierInstrumentIdParam,
            @PathParam("quantite") String quantiteParam) throws Exception {
        try {
            int panierInstrumentId = Integer.parseInt(panierInstrumentIdParam);
            int quantite = Integer.parseInt(quantiteParam);
            panier.modifierLignePanier(panierInstrumentId, quantite);
        } catch (NumberFormatException e) {
            throw new Exception("Erreur dans les parametres");
        }
    }
    
    @GET
    @Path("/payer/{panierid}/{clientid}")
    @Produces("application/json")
    public void payerPanier(@PathParam("panierid") String panierParam, @PathParam("clientid") String clientParam)
            throws Exception {
        try {
            int panierId = Integer.parseInt(panierParam);
            int clientId = Integer.parseInt(clientParam);
            panier.payerPanier(panierId, clientId);
        } catch (NumberFormatException e) {
            throw new Exception("Erreur dans les parametres");
        }
    }
    @GET
    @Path("/annuler/{panierid}/{clientid}")
    @Produces("application/json")
    public void annulerValidationPanier(@PathParam("panierid") String panierParam, @PathParam("clientid") String clientParam)
            throws Exception {
        try {
            int panierId = Integer.parseInt(panierParam);
            int clientId = Integer.parseInt(clientParam);
            panier.annulerValidationPanier(panierId, clientId);
        } catch (NumberFormatException e) {
            throw new Exception("Erreur dans les parametres");
        }
    }

}
