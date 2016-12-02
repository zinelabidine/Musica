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
import com.ecom.musica.entities.Utilisateur;

@XmlRootElement
class SmallUtilisateurBody {
    @XmlElement String login;
    @XmlElement String mdp;
    @XmlElement String email;
}

@XmlRootElement
class UtilisateurPersonalInformationBody {
    @XmlElement int utilisateurid;
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
class UtilisateurPaymentInformationBody {
    @XmlElement int utilisateurid;
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
    public void createUtilisateur(
            SmallUtilisateurBody request
    ) throws Exception {
        utilisateurs.addUtilisateur(request.login, request.mdp, request.email);
    }

    @POST
    @Path("/set/personalinfo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void setClientPersonalInformation(
            UtilisateurPersonalInformationBody request
    ) throws Exception {
        utilisateurs.setUtilisateurPersonalInformation(
                request.utilisateurid,
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
            UtilisateurPaymentInformationBody request
    ) throws Exception {
        utilisateurs.setUtilisateurPaymentInformation(
                request.utilisateurid,
                request.cardname,
                request.cardnumber,
                request.cardmonth,
                request.cardyear
        );
    }
}
