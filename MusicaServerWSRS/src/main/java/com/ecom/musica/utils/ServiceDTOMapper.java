package com.ecom.musica.utils;

import com.ecom.musica.dto.SmallUtilisateurDTO;
import com.ecom.musica.dto.UtilisateurPersonalInformationDTO;
import com.ecom.musica.entities.Utilisateur;

public class ServiceDTOMapper {

	public static Utilisateur mapSmallUtilisateurDtoToUtilisateur(SmallUtilisateurDTO smallUtilisateurDTO) {
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setEmail(smallUtilisateurDTO.getEmail());
		utilisateur.setLogin(smallUtilisateurDTO.getLogin());
		utilisateur.setMdp(smallUtilisateurDTO.getMdp());
		return utilisateur;
	}

	public static UtilisateurPersonalInformationDTO mapUtilisateurToUtilisateurPersonalInformationDTO(
			Utilisateur utilisateur) {

		UtilisateurPersonalInformationDTO utilisateurPersonalInformationDTO = new UtilisateurPersonalInformationDTO();
		utilisateurPersonalInformationDTO.setAddress(utilisateur.getAddress());
		utilisateurPersonalInformationDTO.setCity(utilisateur.getCity());
		utilisateurPersonalInformationDTO.setCountry(utilisateur.getCountry());
		utilisateurPersonalInformationDTO.setEmail(utilisateur.getEmail());
		utilisateurPersonalInformationDTO.setFirstname(utilisateur.getFirstname());
		utilisateurPersonalInformationDTO.setLastname(utilisateur.getLastname());
		utilisateurPersonalInformationDTO.setTel(utilisateur.getTel());
		utilisateurPersonalInformationDTO.setUtilisateurid(utilisateur.getUtilisateurId());
		utilisateurPersonalInformationDTO.setZip(utilisateur.getZip());

		return utilisateurPersonalInformationDTO;
	}
}
