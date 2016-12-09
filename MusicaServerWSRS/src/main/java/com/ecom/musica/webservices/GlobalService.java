package com.ecom.musica.webservices;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.ecom.musica.buisness.ManageCategoryBeanRemote;
import com.ecom.musica.buisness.ManageMarqueBeanRemote;
import com.ecom.musica.dto.InitializeHeaderDTO;

@Stateless
@LocalBean
@Path("/global")
public class GlobalService {
    
    @EJB
    private ManageCategoryBeanRemote categorie;
    
    @EJB
    private ManageMarqueBeanRemote marque;
    
    @GET
    @Path("/initializeheader")
    @Produces("application/json")
    public InitializeHeaderDTO initializeHeader() {
        InitializeHeaderDTO init = new InitializeHeaderDTO();
        init.setCategories(categorie.getAllCategories());
        init.setMarques(marque.getAllMarques());
        return init;
    }

}
