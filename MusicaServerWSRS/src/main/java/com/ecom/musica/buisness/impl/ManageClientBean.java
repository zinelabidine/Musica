package com.ecom.musica.buisness.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ecom.musica.buisness.ManageClientBeanRemote;
import com.ecom.musica.entities.Utilisateur;

@Stateless
public class ManageClientBean implements ManageClientBeanRemote {

    @PersistenceContext(unitName = "EntityManagerPU")
    private EntityManager em;

    @Override
    public Utilisateur getUtilisateur(int utilisateurid) throws Exception {
        return em.find(Utilisateur.class, utilisateurid);
    }

    @Override
    public void addUtilisateur(String login, String mdp, String email) throws Exception {
        em.persist(new Utilisateur(login, mdp, email));
        em.flush();
    }

    @Override
    public void setUtilisateurPersonalInformation(
            int utilisateurid,
            String firstname,
            String lastname,
            String address,
            String city,
            String country,
            String zip,
            String tel,
            String email
    ) throws Exception {
       Utilisateur utilisateur = em.find(Utilisateur.class, utilisateurid);
       if (utilisateur == null) throw new Exception("Client null");
       utilisateur.setFirstname(firstname);
       utilisateur.setLastname(lastname);
       utilisateur.setAddress(address);
       utilisateur.setCity(city);
       utilisateur.setCountry(country);
       utilisateur.setZip(zip);
       utilisateur.setTel(tel);
       utilisateur.setEmail(email);
       em.merge(utilisateur);
    }

    @Override
    public void setUtilisateurPaymentInformation(
            int utilisateurId,
            String cardname,
            String cardnumber,
            String cardmonth,
            String cardyear
    ) throws Exception {
        Utilisateur utilisateur = em.find(Utilisateur.class, utilisateurId);
        if (utilisateur == null) throw new Exception("Client null");
        utilisateur.setCardname(cardname);
        utilisateur.setCardnumber(cardnumber);
        utilisateur.setCardmonth(cardmonth);
        utilisateur.setCardyear(cardyear);
        em.merge(utilisateur);
    }

    @Override
    public void deleteUtilisateur(int utilisateurId) throws Exception {
        // TODO Auto-generated method stub
        
    }

}
