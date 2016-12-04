package com.ecom.musica.webservices;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.ecom.musica.buisness.ManageClientBeanRemote;
import com.ecom.musica.dto.SmallUtilisateurDTO;
import com.ecom.musica.dto.UtilisateurPaymentInformationDTO;
import com.ecom.musica.dto.UtilisateurPersonalInformationDTO;
import com.ecom.musica.entities.Utilisateur;

@Stateless
@LocalBean
@Path("/client")
public class ClientService {

	@EJB
	private ManageClientBeanRemote utilisateurs;

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Utilisateur getUtilisateur(@QueryParam("id") int id) throws Exception {
		return utilisateurs.getUtilisateur(id);
	}

	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void createUtilisateur(SmallUtilisateurDTO request) throws Exception {
		utilisateurs.addUtilisateur(request.getLogin(), request.getMdp(), request.getEmail());
	}

	@POST
	@Path("/set/personalinfo")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void setClientPersonalInformation(UtilisateurPersonalInformationDTO request) throws Exception {
		utilisateurs.setUtilisateurPersonalInformation(request.getUtilisateurid(), request.getFirstname(),
				request.getLastname(), request.getAddress(), request.getCity(), request.getCountry(), request.getZip(),
				request.getTel(), request.getEmail());
	}

	@POST
	@Path("/set/paymentinfo")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void setClientpaymentInformation(UtilisateurPaymentInformationDTO request) throws Exception {
		utilisateurs.setUtilisateurPaymentInformation(request.getUtilisateurid(), request.getCardname(),
				request.getCardnumber(), request.getCardmonth(), request.getCardyear());
	}
}
