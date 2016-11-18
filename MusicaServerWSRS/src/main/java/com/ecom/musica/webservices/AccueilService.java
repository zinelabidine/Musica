package com.ecom.musica.webservices;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.ecom.musica.buisness.ManageInstrumentBeanRemote;
import com.ecom.musica.dto.InitializeHomeContentDTO;

@Stateless
@LocalBean
@Path("/accueil")
public class AccueilService {
    @EJB
    private ManageInstrumentBeanRemote instrument;

    @GET
    @Path("/initializehomecontent")
    @Produces("application/json")
    public InitializeHomeContentDTO initializeHomeContent() {
        InitializeHomeContentDTO init = new InitializeHomeContentDTO();
        init.instrumentsBestSales = instrument.getInstrumentsBestSales();
        init.instrumentsPromotions = instrument.getInstrumentsPromotion();
        return init;
    }

}
