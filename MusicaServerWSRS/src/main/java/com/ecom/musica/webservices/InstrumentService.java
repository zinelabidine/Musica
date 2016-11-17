package com.ecom.musica.webservices;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.ecom.musica.buisness.ManageCategoryBeanRemote;
import com.ecom.musica.buisness.ManageInstrumentBeanRemote;
import com.ecom.musica.dto.InitializeHeaderDTO;
import com.ecom.musica.dto.InitializeHomeContentDTO;
import com.ecom.musica.entities.Instrument;

@Stateless
@LocalBean
@Path("/instrument")
public class InstrumentService {

/*	
	@EJB
	private ManageInstrumentBeanRemote instrument;
	
	@EJB
	private ManageCategoryBeanRemote categorie;
	
    @GET
    @Path("/all")
    @Produces("application/json")
    public List<Instrument> getAllInstruments() {
        return instrument.getAllInstruments();
    }
    
    @GET
    @Path("/initializehome")
    @Produces("application/json")
    public InitializeHomeDTO initializeHome() {
    	InitializeHomeDTO init = new InitializeHomeDTO();
         init.InstrumentsBestSales =  instrument.getInstrumentsBestSales();
         init.InstrumentsPromotions = instrument.getInstrumentsPromotion();
         init.Categories = categorie.getAllCategories();
         return init;
    }

    @GET
    @Path("/find")
    @Produces(MediaType.APPLICATION_JSON)
    public Instrument getInstrumentWithID(@QueryParam("id") int instrument_id){
        return instrument.getInstrumentWithId(instrument_id);
    }
*/

}
