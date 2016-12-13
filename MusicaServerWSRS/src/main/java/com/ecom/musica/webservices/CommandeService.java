package com.ecom.musica.webservices;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.ecom.musica.buisness.ManageCommandeBeanRemote;
import com.ecom.musica.buisness.ManageMailBeanRemote;
import com.ecom.musica.buisness.impl.ManageMailBean;
import com.ecom.musica.dto.FactureCommandeDTO;
import com.ecom.musica.entities.Commande;

@Stateless
@LocalBean
@Path("/commande")
public class CommandeService {
    @EJB
    private ManageCommandeBeanRemote commande;
    @EJB
    private ManageMailBeanRemote mail;

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
            commande.modifierLigneCommande(commandeInstrumentId, quantite);
        } catch (NumberFormatException e) {
            throw new Exception("Erreur dans les parametres");
        }
    }

    @POST
    @Path("/facture")
    @Produces("application/json")
    public void factureCommande(FactureCommandeDTO facturedto) throws Exception {
        // System.out.println(facturedto.getCommandeid());
        // System.out.println(facturedto.getFacturebase64());
        mail.sendMail(facturedto.getFacturebase64(), facturedto.getCommandeid());
    }
    

    @GET
    @Path("/getcommandesbyutilisateur/{utilisateurid}")
    @Produces("application/json")
    public List<Commande> getAllCommande(@PathParam("utilisateurid") String utilisateurParam) throws Exception {
        try {
            int utilisateurId = Integer.parseInt(utilisateurParam);
            return commande.getCommandesByUtilisateur(utilisateurId);
        } catch (NumberFormatException e) {
            throw new Exception("Erreur dans les parametres");
        }
    }
    
//    @GET
//    @Path("/getcommandesbyutilisateur/{utilisateurid}")
//    @Produces("application/json")
//    public void annulerValidationPanier(@PathParam("utilisateurid") String utilisateurParam)
//            throws Exception {
//        try {
//            int utilisateurId = Integer.parseInt(utilisateurParam);
//            commande.getCommandesByUtilisateur(utilisateurId);
//        } catch (NumberFormatException e) {
//            throw new Exception("Erreur dans les parametres");
//        }
//    }
}
