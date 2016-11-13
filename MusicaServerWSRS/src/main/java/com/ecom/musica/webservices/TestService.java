package com.ecom.musica.webservices;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.ecom.musica.buisness.ManageInstrumentBeanRemote;
import com.ecom.musica.buisness.impl.ManageInstrumentBean;

/**
 * Session Bean implementation class HelloJAXRSWebService
 */
@Stateless
@LocalBean
@Path("/test")
public class TestService {
	@EJB
	private ManageInstrumentBeanRemote instrument;

	/**
	 * Default constructor.
	 */
	public TestService() {
		// TODO Auto-generated constructor stub
	}

	@GET
	@Path("hello")
	public String sayHello() {
		return "Hello";

	}
	
	@GET
	@Path("hello2")
	public String sayHello2() {
		return "Hello2";

	}

}
