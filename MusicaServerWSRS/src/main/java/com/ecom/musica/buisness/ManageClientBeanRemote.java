package com.ecom.musica.buisness;

import javax.ejb.Remote;

import com.ecom.musica.entities.Utilisateur;

@Remote
public interface ManageClientBeanRemote {

    public Utilisateur getUtilisateur(int utilisateurid) throws Exception;

    public void addUtilisateur(
            String login,
            String mdp,
            String email
            ) throws Exception;

    public void setUtilisateurPersonalInformation(
            int utilisateurid,
            String firstname,
            String lastname,
            String addres,
            String vity,
            String country,
            String zip,
            String tel,
            String email
            ) throws Exception;

    public void setUtilisateurPaymentInformation(
            int utilisateurid,
            String cardname,
            String cardnumber,
            String date,
            String code
            ) throws Exception;

    public void deleteUtilisateur(int utilisateurid) throws Exception;
}
