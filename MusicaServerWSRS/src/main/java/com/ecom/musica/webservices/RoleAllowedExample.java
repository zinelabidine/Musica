package com.ecom.musica.webservices;

import javax.annotation.security.RolesAllowed;
import javax.ejb.LocalBean;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.ecom.musica.dto.TestDTO;

//@ManagedBean
@LocalBean
@RolesAllowed({"ADMIN", "CLIENT"})
@Path("/adminAllowed")
public class RoleAllowedExample {

	@RolesAllowed("ADMIN")
	@GET
	@Path("/test")
	@Produces("application/json")
	public TestDTO initializeHomeContent() {
		TestDTO testDTO = new TestDTO();
		testDTO.setStringTest("Youness");
		testDTO.setStringTest2("Hardi");
		return testDTO;
	}

}
