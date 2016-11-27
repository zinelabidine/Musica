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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.ecom.musica.buisness.ManageClientBeanRemote;
import com.ecom.musica.entities.Client;

@XmlRootElement
class SmallClientBody {
    @XmlElement String login;
    @XmlElement String mdp;
    @XmlElement String email;
}

@XmlRootElement
class ClientPersonalInformationBody {
    @XmlElement int clientid;
    @XmlElement String firstname;
    @XmlElement String lastname;
    @XmlElement String address;
    @XmlElement String city;
    @XmlElement String country;
    @XmlElement String zip;
    @XmlElement String tel;
    @XmlElement String email;
}

@XmlRootElement
class ClientPaymentInformationBody {
    @XmlElement int clientid;
    @XmlElement String cardname;
    @XmlElement String cardnumber;
    @XmlElement String cardmonth;
    @XmlElement String cardyear;
}

@Stateless
@LocalBean
@Path("/client")
public class ClientService {
    
    @EJB
    private ManageClientBeanRemote clients;

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Client getClient(@QueryParam("id") int id) throws Exception {
        return clients.getClient(id);
    }

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void createClient(
            SmallClientBody request
    ) throws Exception {
        clients.addClient(request.login, request.mdp, request.email);
    }

    @POST
    @Path("/set/personalinfo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void setClientPersonalInformation(
            ClientPersonalInformationBody request
    ) throws Exception {
        clients.setClientPersonalInformation(
                request.clientid,
                request.firstname,
                request.lastname,
                request.address,
                request.city,
                request.country,
                request.zip,
                request.tel,
                request.email
        );
    }

    @POST
    @Path("/set/paymentinfo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void setClientpaymentInformation(
            ClientPaymentInformationBody request
    ) throws Exception {
        clients.setClientPaymentInformation(
                request.clientid,
                request.cardname,
                request.cardnumber,
                request.cardmonth,
                request.cardyear
        );
    }
}
