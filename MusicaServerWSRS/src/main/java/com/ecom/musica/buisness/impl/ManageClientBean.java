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
        return em.find(Client.class, clientid);
    }

    @Override
    public void addClient(String login, String mdp, String email) throws Exception {
        em.persist(new Client(login, mdp, email));
        em.flush();
    }

    @Override
    public void setClientPersonalInformation(
            int clientid,
            String firstname,
            String lastname,
            String address,
            String city,
            String country,
            String zip,
            String tel,
            String email
    ) throws Exception {
       Client client = em.find(Client.class, clientid);
       if (client == null) throw new Exception("Client null");
       client.setFirstname(firstname);
       client.setLastname(lastname);
       client.setAddress(address);
       client.setCity(city);
       client.setCountry(country);
       client.setZip(zip);
       client.setTel(tel);
       client.setEmail(email);
       em.merge(client);
    }

    @Override
    public void setClientPaymentInformation(
            int clientid,
            String cardname,
            String cardnumber,
            String cardmonth,
            String cardyear
    ) throws Exception {
        Client client = em.find(Client.class, clientid);
        if (client == null) throw new Exception("Client null");
        client.setCardname(cardname);
        client.setCardnumber(cardnumber);
        client.setCardmonth(cardmonth);
        client.setCardyear(cardyear);
        em.merge(client);
    }

    @Override
    public void deleteClient(int clientid) throws Exception {
        // TODO Auto-generated method stub
        
    }

}