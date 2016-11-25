package com.ecom.musica.webservices;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.ecom.musica.buisness.ManageClientBeanRemote;

@XmlRootElement
class SmallClientBody {
    @XmlElement String login;
    @XmlElement String mdp;
    @XmlElement String email;
}

@Stateless
@LocalBean
@Path("/client")
public class ClientService {
    
    @EJB
    private ManageClientBeanRemote clients;

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void createClient(
            SmallClientBody request
    ) throws Exception {
        clients.addClient(request.login, request.mdp, request.email);
    }
}
