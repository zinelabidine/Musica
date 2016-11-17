package com.ecom.musica.webservices;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.ecom.musica.buisness.ManageCategoryBeanRemote;
import com.ecom.musica.buisness.ManageInstrumentBeanRemote;
import com.ecom.musica.dto.InitializeHeaderDTO;
import com.ecom.musica.dto.InitializeHomeContentDTO;
import com.ecom.musica.entities.Instrument;

@Stateless
@LocalBean
@Path("/instrument")
public class InstrumentService {


}
