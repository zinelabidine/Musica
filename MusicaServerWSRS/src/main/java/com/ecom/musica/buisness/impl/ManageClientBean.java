package com.ecom.musica.buisness.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ecom.musica.buisness.ManageClientBeanRemote;
import com.ecom.musica.entities.Client;

@Stateless
public class ManageClientBean implements ManageClientBeanRemote {

    @PersistenceContext(unitName = "EntityManagerPU")
    private EntityManager em;

    @Override
    public Client getClient(int clientid) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addClient(String login, String mdp, String email) throws Exception {
        System.out.println("Login; " + login);
        System.out.println("Mdp; " + mdp);
        System.out.println("Email; " + email);
        em.persist(new Client(login, mdp, email));
        em.flush();
    }

    @Override
    public void setClientPersonalInformation(String firstname, String lastname, String addres, String vity,
            String country, String zip, String tel) throws Exception {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setClientPaymentInformation(String cardname, String cardnumber, String date, String code)
            throws Exception {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteClient(int clientid) throws Exception {
        // TODO Auto-generated method stub
        
    }

}
