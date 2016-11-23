package com.ecom.musica.webservices;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.ecom.musica.buisness.ManageCommandeBeanRemote;
import com.ecom.musica.entities.Commande;

@Stateless
@LocalBean
@Path("/commande")
public class CommandeService {
    @EJB
    private ManageCommandeBeanRemote commande;

    @GET
    @Path("/getcommande/{commandeid}")
    @Produces("application/json")
    public Commande getCommandeEnCours(@PathParam("commandeid") String commandeParam) throws Exception {
        try {
            int commandeId = Integer.parseInt(commandeParam);
            return commande.getCommandeEnCours(commandeId);
        } catch (NumberFormatException e) {
            throw new Exception("Erreur dans les parametres");
        }
    }

    @GET
    @Path("/payer/{commandeid}/{clientid}")
    @Produces("application/json")
    public void payerCommande(@PathParam("commandeid") String commandeParam, @PathParam("clientid") String clientParam)
            throws Exception {
        try {
            int commandeId = Integer.parseInt(commandeParam);
            int clientId = Integer.parseInt(clientParam);
            commande.payerCommande(commandeId, clientId);
        } catch (NumberFormatException e) {
            throw new Exception("Erreur dans les parametres");
        }
    }

    @GET
    @Path("/getcommandes/{clientid}")
    @Produces("application/json")
    public List<Commande> getListCommande(@PathParam("clientid") String clientParam) throws Exception {
        try {
            int clientId = Integer.parseInt(clientParam);
            return commande.getListCommande(clientId);
        } catch (NumberFormatException e) {
            throw new Exception("Erreur dans les parametres");
        }
    }

    @GET
    @Path("/delete/{commandeid}")
    @Produces("application/json")
    public void abondonnerCommande(@PathParam("commandeid") String commandeParam) throws Exception {
        try {
            int commandeId = Integer.parseInt(commandeParam);
            commande.abondonnerCommande(commandeId);
        } catch (NumberFormatException e) {
            throw new Exception("Erreur dans les parametres");
        }
    }

    @GET
    @Path("/deleteligne/{commandeinstrumentid}")
    @Produces("application/json")
    public void supprimerLigneCommande(@PathParam("commandeinstrumentid") String commandeInstrumentParam)
            throws Exception {
        try {
            int commandeInstrumentId = Integer.parseInt(commandeInstrumentParam);
            commande.supprimerLigneCommande(commandeInstrumentId);
        } catch (NumberFormatException e) {
            throw new Exception("Erreur dans les parametres");
        }
    }

    @GET
    @Path("/modifierligne/{commandeinstrumentid}/{quantite}")
    @Produces("application/json")
    public void modifierLigneCommande(@PathParam("commandeinstrumentid") String commandeInstrumentParam,
            @PathParam("quantite") String quantiteParam) throws Exception {
        try {
            int commandeInstrumentId = Integer.parseInt(commandeInstrumentParam);
            int quantite = Integer.parseInt(quantiteParam);
            commande.modifierLigneCommande(commandeInstrumentId,quantite);
        } catch (NumberFormatException e) {
            throw new Exception("Erreur dans les parametres");
        }
    }
}
