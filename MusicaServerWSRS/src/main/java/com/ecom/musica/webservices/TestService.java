package com.ecom.musica.webservices;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.ecom.musica.buisness.ManageInstrumentBeanRemote;

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
	@Path("server")
	public String getAdressIp(){
		try {
			System.out.println(InetAddress.getLocalHost().getHostAddress());
			return InetAddress.getLocalHost().getHostAddress();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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
