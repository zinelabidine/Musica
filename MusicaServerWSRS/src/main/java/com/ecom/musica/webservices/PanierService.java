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

@Stateless
@LocalBean
@Path("/panier")
public class PanierService {
    @EJB
    private ManagePanierBeanRemote panier;

    @GET
    @Path("/add/{clientid}/{instrumentid}/{quantiteParam}")
    @Produces("application/json")
    public void addToPanier(@PathParam("clientid") String clientParam,
            @PathParam("instrumentid") String instrumentParam, @PathParam("quantite") String quantiteParam) throws Exception{
        try {
            int clientId = Integer.parseInt(clientParam);
            int instrumentId = Integer.parseInt(instrumentParam);
            int quantite = Integer.parseInt(quantiteParam);
            panier.addToPanier(clientId,instrumentId,quantite);
        } catch (NumberFormatException e) {

        }
    }
}
