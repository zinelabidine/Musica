package com.ecom.musica.webservices;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
 
/**
 * Session Bean implementation class HelloJAXRSWebService
 */
@Stateless
@LocalBean
@Path("/test")
public class TestService {
 
   /**
     * Default constructor. 
     */
    public TestService() {
        // TODO Auto-generated constructor stub
    }

    @GET
    public String sayHello() {
        return "Hello";
    }

    
}
