package com.ecom.musica.buisness;

import javax.ejb.Remote;

import com.ecom.musica.entities.Client;

@Remote
public interface ManageClientBeanRemote {

    public Client getClient(int clientid) throws Exception;

    public void addClient(
            String login,
            String mdp,
            String email
            ) throws Exception;

    public void setClientPersonalInformation(
            String firstname,
            String lastname,
            String addres,
            String vity,
            String country,
            String zip,
            String tel
            ) throws Exception;

    public void setClientPaymentInformation(
            String cardname,
            String cardnumber,
            String date,
            String code
            ) throws Exception;

    public void deleteClient(int clientid) throws Exception;
}
