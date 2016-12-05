package com.ecom.musica.webservices;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.ecom.musica.buisness.ManageUtilisateurBeanRemote;
import com.ecom.musica.dao.ProfilDao;
import com.ecom.musica.dto.SmallUtilisateurDTO;
import com.ecom.musica.dto.UtilisateurPersonalInformationDTO;
import com.ecom.musica.entities.Profil;
import com.ecom.musica.entities.Utilisateur;
import com.ecom.musica.utils.Role;
import com.ecom.musica.utils.ServiceDTOMapper;

@LocalBean
@Path("/auth")
public class AuthentificationService {

	@EJB
	private ManageUtilisateurBeanRemote manageUtilisateurBeanRemote;

	@EJB
	private ProfilDao profilDao;

	@POST
	@Path("/register")
	@Produces("application/json")
	public UtilisateurPersonalInformationDTO registerClient(SmallUtilisateurDTO smallUtilisateurDTO) {
		Utilisateur utilisateur = ServiceDTOMapper.mapSmallUtilisateurDtoToUtilisateur(smallUtilisateurDTO);
		// utilisateur.setProfil(profil);
		Profil profil = profilDao.findByLibelle(Role.CLIENT);
		if (profil != null) {
			utilisateur.setProfil(profil);
		} else {
			profil = new Profil();
			profil.setLibelle(Role.CLIENT);
			profilDao.persist(profil);
			utilisateur.setProfil(profil);
		}

		Utilisateur utilisateurSaved = manageUtilisateurBeanRemote.registerUtilisateur(utilisateur);
		return ServiceDTOMapper.mapUtilisateurToUtilisateurPersonalInformationDTO(utilisateurSaved);

	}

	@RolesAllowed({ Role.CLIENT, Role.ADMIN, Role.SUPER_ADMIN })
	@POST
	@Path("/login")
	@Produces("application/json")
	public UtilisateurPersonalInformationDTO getClient(SmallUtilisateurDTO smallUtilisateurDTO) {
		Utilisateur utilisateur = manageUtilisateurBeanRemote.getUtilisateurByLogin(smallUtilisateurDTO.getLogin());
		return ServiceDTOMapper.mapUtilisateurToUtilisateurPersonalInformationDTO(utilisateur);
	}

}
