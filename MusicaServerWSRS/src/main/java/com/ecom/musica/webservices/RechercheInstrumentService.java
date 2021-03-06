package com.ecom.musica.webservices;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.ecom.musica.buisness.ManageInstrumentBeanRemote;
import com.ecom.musica.entities.Instrument;

@Stateless
@LocalBean
@Path("/instrument")
public class RechercheInstrumentService {
    
    @EJB
    private ManageInstrumentBeanRemote instrument;

    @GET
    @Path("/findwithid")
    @Produces(MediaType.APPLICATION_JSON)
    public Instrument getInstrumentWithID(@QueryParam("id") int instrument_id){
        return instrument.getInstrumentWithId(instrument_id);
    }

    @GET
    @Path("/findwithkey")
    @Produces("application/json")
    public List<Instrument> getInstrumentsWithKey( @QueryParam("key") String instrument_key){
    	List<Instrument> instruments  = instrument.getInstrumentsWithKey(instrument_key);
    	return instruments;
    }
    
    @GET
    @Path("/findadvanced")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Instrument> findInstruments(
            @QueryParam("marque") String marque,
            @QueryParam("categorie") String categorie,
            @QueryParam("key") String instrument_ref
            ){
        return instrument.findInstruments(marque,categorie,instrument_ref);
    }
}
