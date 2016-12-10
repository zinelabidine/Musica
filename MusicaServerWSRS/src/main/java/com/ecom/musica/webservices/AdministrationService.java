package com.ecom.musica.webservices;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.ecom.musica.buisness.ManageCategoryBeanRemote;
import com.ecom.musica.buisness.ManageInstrumentBeanRemote;
import com.ecom.musica.buisness.ManageMarqueBeanRemote;
import com.ecom.musica.entities.Categorie;
import com.ecom.musica.entities.CommandeInstrument;
import com.ecom.musica.entities.Instrument;
import com.ecom.musica.entities.Marque;
import com.ecom.musica.entities.Musicien;
import com.ecom.musica.entities.PanierInstrument;
import com.ecom.musica.entities.Promotion;
import com.fasterxml.jackson.annotation.JsonIgnore;

@XmlRootElement
class InstrumentInformationBody {
    @XmlElement
    String marqueLibelle;
    @XmlElement
    String categorieLibelle;
    @XmlElement
    String reference;
    @XmlElement
    int quantite;
    @XmlElement
    float prix;
    @XmlElement
    String description;
    @XmlElement
    String image;
}

@Stateless
@LocalBean
@Path("/administration")
public class AdministrationService {
    @EJB
    private ManageInstrumentBeanRemote instrument;
    
    @EJB
    private ManageMarqueBeanRemote marque;
    
    @EJB
    private ManageCategoryBeanRemote categorie;

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void createUtilisateur(InstrumentInformationBody request) throws Exception {
    	int marqueId = marque.getMarqueId(request.marqueLibelle);
    	int categorieId = categorie.getCategorieId(request.categorieLibelle);
        instrument.addInstrument(marqueId, categorieId, request.reference, request.quantite,
                request.prix, request.description, request.image.toString());
        
    }
}
